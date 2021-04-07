package pe.com.claro.service.experience.query.stock.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class InternalRootRequest {
	private ConsultaStockRequestType consultaStockRequestType;
}
