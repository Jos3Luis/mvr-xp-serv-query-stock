package pe.com.claro.service.experience.query.stock.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RefreshScope
@Component
@ConfigurationProperties(prefix = "application.properties")
public class QueryStockProperties {
  private String idTransaccion;
  private String msgid;
  private String userId;
  private String timestamp;
  private Integer stateOk;
  private String codeResponseOk;
  private String userIdValue;
  private Map<String, String> erroCodeMap;
  private String tecnicalCode;
  private String tecnicalMessage;
  private String functionalCode;
  private String functionalMessage;
  private Integer isMock;
}
