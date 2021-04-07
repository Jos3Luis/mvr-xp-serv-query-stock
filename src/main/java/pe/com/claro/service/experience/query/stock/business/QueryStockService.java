package pe.com.claro.service.experience.query.stock.business;

import java.util.List;

import pe.com.claro.maverick.core.model.HttpHeadersRequest;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockRequestDTO;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockResponseDTO;
import reactor.core.publisher.Mono;

public interface QueryStockService {
  Mono<List<QueryStockResponseDTO>> execute(QueryStockRequestDTO generateDocumentRequest,
      HttpHeadersRequest headersRequest);
}
