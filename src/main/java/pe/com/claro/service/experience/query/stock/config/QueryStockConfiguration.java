package pe.com.claro.service.experience.query.stock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import pe.com.claro.maverick.core.http.util.MaverickHttpClient;
import pe.com.claro.service.experience.query.stock.proxy.QueryStockResourceApi;

@Configuration
public class QueryStockConfiguration {

  @Bean
  QueryStockResourceApi customerResourceApi(
      @Value("${application.properties.rest.claro.base-url}") String apiBaseUrl,
      @Value("${application.properties.rest.claro.connect-timeout}") long connectTimeout,
      @Value("${application.properties.rest.claro.read-timeout}") long readTimeout,
      @Value("${application.properties.rest.claro.write-timeout}") long writeTimeout,
      OkHttpClient.Builder builder) {
    return MaverickHttpClient.builder().connectTimeout(connectTimeout).readTimeout(readTimeout)
        .writeTimeout(writeTimeout).baseUrl(apiBaseUrl).clientBuilder(builder)
        .buildProxy(QueryStockResourceApi.class);
  }
}
