package pe.com.claro.service.experience.query.stock.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.claro.maverick.core.model.HttpHeadersRequest;
import pe.com.claro.service.experience.query.stock.business.impl.QueryStockServiceImpl;
import pe.com.claro.service.experience.query.stock.config.QueryStockProperties;
import pe.com.claro.service.experience.query.stock.internal.InternalRootResponse;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockRequestDTO;
import pe.com.claro.service.experience.query.stock.proxy.QueryStockResourceApi;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class CheckStockServiceImplTest {
  @Mock
  private QueryStockResourceApi resourceApi;
  @Mock
  private QueryStockProperties properties;

  private QueryStockServiceImpl service;
  private HttpHeadersRequest headersRequest;

  private Map<String, String> mapHeader;
  
  InternalRootResponse responseInternal;
  InternalRootResponse responseInternalFunctionalError;
  InternalRootResponse responseInternalTechnicalError;
  QueryStockRequestDTO requestDto;
 

  @Before
  public void setUp() throws IOException {
    properties = new QueryStockProperties();
    properties.setIsMock(0);
    headersRequest = new HttpHeadersRequest();
    headersRequest.setRequestId("75925eb4-5128-4e7d-9f55-683479274b48");
    headersRequest.setApplicationCode("app-demo");
    headersRequest.setRequestDate("Wed, 21 Oct 2015 07:28:00 GMT");

    mapHeader = new HashMap<>();
    mapHeader.put(properties.getIdTransaccion(), "75925eb4-5128-4e7d-9f55-683479274b48");
    mapHeader.put(properties.getMsgid(), "Mensaje ID");
    mapHeader.put(properties.getUserId(), "123456");
    mapHeader.put(properties.getTimestamp(), "Wed, 21 Oct 2015 07:28:00 GMT");
    mapHeader.put("accept", "application/json");
    mapHeader.put("Content-Type", "application/json");
    mapHeader.put("aplicacion", "app-demo");
     
	  
    requestDto=new ObjectMapper().readValue(
	        new ClassPathResource("json/request-dto.json").getInputStream(),
	        QueryStockRequestDTO.class);
    
    responseInternal= new ObjectMapper().readValue(
	        new ClassPathResource("json/response-internal.json").getInputStream(),
	        InternalRootResponse.class);  
    
	responseInternalFunctionalError=new ObjectMapper().readValue(
	        new ClassPathResource("json/response-internal-functional.json").getInputStream(),
	        InternalRootResponse.class);
	  
	responseInternalTechnicalError=new ObjectMapper().readValue(
	        new ClassPathResource("json/response-internal-tecnical.json").getInputStream(),
	        InternalRootResponse.class);
	  
    
    resourceApi = Mockito.mock(QueryStockResourceApi.class);
    service = new QueryStockServiceImpl(properties, resourceApi);
  }

  @Test
  public void testOk() {
    Mockito.when(resourceApi.executeService(Mockito.any(), Mockito.any()))
        .thenReturn(Mono.just(responseInternal));
    
    StepVerifier.create(service.execute( requestDto , headersRequest))
        .expectNextMatches(p -> p.size()>0).expectComplete().verify();

    assertNotNull(service);
  }

  @Test
  public void testFunctionalErrorCodigoRespuesta() {
    Mockito.when(resourceApi.executeService(Mockito.any(), Mockito.any()))
        .thenReturn(Mono.just(responseInternalFunctionalError)); 
    StepVerifier.create(service.execute( requestDto , headersRequest))
        .expectError().verify();

    assertNotNull(service);
  } 

  @Test
  public void testTecnicalError() { 
	  
    Mockito.when(resourceApi.executeService(Mockito.any(), Mockito.any()))
        .thenReturn(Mono.just(responseInternalTechnicalError));
    
    StepVerifier.create(service.execute( requestDto , headersRequest))
        .expectError().verify();

    assertNotNull(service);
  }

  @Test
  public void testTecnicalErrorData() { 
	 
	  responseInternalTechnicalError.getConsultarStockResponseType().setResponseData(null);
	  responseInternalTechnicalError.getConsultarStockResponseType().getResponseStatus().setCodigoRespuesta("0");
    Mockito.when(resourceApi.executeService(Mockito.any(), Mockito.any()))
        .thenReturn(Mono.just(responseInternalTechnicalError));
    
    StepVerifier.create(service.execute( requestDto , headersRequest))
        .expectError().verify();

    assertNotNull(service);
  }
}

