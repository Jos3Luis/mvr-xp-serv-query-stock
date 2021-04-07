package pe.com.claro.service.experience.query.stock.proxy;

import java.util.Map;

import pe.com.claro.service.experience.query.stock.internal.InternalRootRequest;
import pe.com.claro.service.experience.query.stock.internal.InternalRootResponse;
import reactor.core.publisher.Mono;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface QueryStockResourceApi {
  @POST("consultaStock")
  Mono<InternalRootResponse> executeService(@Body InternalRootRequest request,
      @HeaderMap Map<String, String> headers);
}
