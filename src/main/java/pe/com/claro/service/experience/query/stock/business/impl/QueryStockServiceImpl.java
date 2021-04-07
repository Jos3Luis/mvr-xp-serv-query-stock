package pe.com.claro.service.experience.query.stock.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.claro.maverick.core.constants.ErrorCategory;
import pe.com.claro.maverick.core.constants.ErrorCodetype;
import pe.com.claro.maverick.core.constants.HttpHeadersKey;
import pe.com.claro.maverick.core.exception.MaverickException;
import pe.com.claro.maverick.core.exception.MaverickExceptionBuilder;
import pe.com.claro.maverick.core.model.HttpHeadersRequest;
import pe.com.claro.service.experience.query.stock.business.QueryStockService;
import pe.com.claro.service.experience.query.stock.config.QueryStockProperties;
import pe.com.claro.service.experience.query.stock.internal.InternalRootRequest;
import pe.com.claro.service.experience.query.stock.internal.InternalRootResponse;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockRequestDTO;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockResponseDTO;
import pe.com.claro.service.experience.query.stock.proxy.QueryStockResourceApi;
import pe.com.claro.service.experience.query.stock.utils.Utils;
import reactor.core.publisher.Mono;

@RefreshScope
@Slf4j
@Service
public class QueryStockServiceImpl implements QueryStockService {

  @Value("${spring.application.name}")
  private String componentName;

  @Value("${application.properties.isMock}")
  private int isMock;

  private final QueryStockProperties properties;
  private final QueryStockResourceApi resourceApi;

  public QueryStockServiceImpl(QueryStockProperties properties,
      QueryStockResourceApi resourceApi) {
    this.properties = properties;
    this.resourceApi = resourceApi;
  }

  @Override
  public Mono<List<QueryStockResponseDTO>> execute(QueryStockRequestDTO bioConsultRequest,
      HttpHeadersRequest headersRequest) {
    InternalRootRequest request=Utils.parseFromDto(bioConsultRequest);
    Map<String, String> map= this.obtenerHeadersMap(headersRequest);
	return resourceApi.executeService(request, map).flatMap(this::getBodyService).onErrorResume(this::technicalError);
  } 
  private Mono<List<QueryStockResponseDTO>> getBodyService(InternalRootResponse p){
	  if (isMock==NumberUtils.INTEGER_ONE) {
		  log.info("======= Mock is activated ================");
			return Mono.empty();//just(Utils.getMock());
	  }
	  if (p!=null && p.getConsultarStockResponseType()!=null) {
			int rpta= Integer.parseInt(p.getConsultarStockResponseType().getResponseStatus().getCodigoRespuesta());
			if (rpta==0) { 
				return Mono.just(Utils.parseFromService(p));
			}else if (rpta>0) {
				return this.FunctionalError(new Throwable(p.getConsultarStockResponseType().getResponseStatus().getMensajeRespuesta()));
			}else {
				return this.technicalError(new Throwable(properties.getTecnicalMessage()));
			}
		}
	  return this.technicalError(new Throwable(properties.getTecnicalMessage())); 
  }
 

  private Mono<List<QueryStockResponseDTO>> FunctionalError(Throwable error) {
    return MaverickExceptionBuilder.builder().systemCode(properties.getFunctionalCode())
        .category(ErrorCategory.EXTERNAL_ERROR).description(error.getMessage())
        .cause(new Throwable(MDC.get(HttpHeadersKey.REQUEST_ID)))
        .errorType(ErrorCodetype.FUNCTIONAL_ERROR.getKey()).addDetail()
        .withDescription(MDC.get(HttpHeadersKey.REQUEST_ID)).withComponent(componentName).push()
        .buildAsMono();
  }

  private Mono<List<QueryStockResponseDTO>> technicalError(Throwable error) {
    if (error instanceof MaverickException) {
      return Mono.error(error);
    } else {
      return MaverickExceptionBuilder.builder().systemCode(properties.getTecnicalCode())
          .category(ErrorCategory.EXTERNAL_ERROR)
          .description(new StringBuilder().append(MDC.get(HttpHeadersKey.REQUEST_ID)).append(" ")
              .append(error.getMessage()).toString())
          .cause(error).errorType(ErrorCodetype.TECHNICAL_ERROR.getKey()).addDetail()
          .withComponent(componentName).push().buildAsMono();
    }
  }

  private Map<String, String> obtenerHeadersMap(HttpHeadersRequest headers) {
    Map<String, String> mapHeader = new HashMap<>();
    mapHeader.put(properties.getIdTransaccion(), headers.getRequestId());
    mapHeader.put(properties.getMsgid(), headers.getRequestId());
    mapHeader.put(properties.getUserId(), headers.getApplicationCode());
    mapHeader.put(properties.getTimestamp(), headers.getRequestDate());
    mapHeader.put("accept", "application/json");
    mapHeader.put("Content-Type", "application/json");
    mapHeader.put("aplicacion", headers.getApplicationCode());
    return mapHeader;
  }
}
