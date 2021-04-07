package pe.com.claro.service.experience.query.stock.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import pe.com.claro.maverick.annotations.HttpHeadersMapping;
import pe.com.claro.maverick.core.exception.MaverickException;
import pe.com.claro.maverick.core.model.HttpHeadersRequest;
import pe.com.claro.service.experience.query.stock.business.QueryStockService;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockRequestDTO;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockResponseDTO;
import pe.com.claro.service.experience.query.stock.utils.constants.Constants;
import reactor.core.publisher.Mono;

@RestController
@Api(tags = "Portability")
@RequiredArgsConstructor
public class QueryStockController { 
	private final QueryStockService config;

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = Constants.MSG_200_CONTROLLER, response = QueryStockResponseDTO.class),
			@ApiResponse(code = 400, message = Constants.MSG_400_CONTROLLER, response = MaverickException.class),
			@ApiResponse(code = 412, message = Constants.MSG_412_CONTROLLER, response = MaverickException.class),
			@ApiResponse(code = 500, message = Constants.MSG_500_CONTROLLER, response = MaverickException.class) })
	@ApiOperation(value = "Microservicio de experiencia que se encarga de la consulta del stock", notes = "classpath:swagger/notes/mvr-xp-serv-query-stock.md")
	@PostMapping(value = "/query/stock", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public Mono<List<QueryStockResponseDTO>> executeStock(@Validated @RequestBody QueryStockRequestDTO request,
			@HttpHeadersMapping HttpHeadersRequest headers) {
		return config.execute(request, headers);
	}
}
