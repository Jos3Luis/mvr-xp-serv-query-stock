package pe.com.claro.service.experience.query.stock.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryStockResponseDTO {
	@ApiModelProperty(name = "officeCode", value = "Codigo de la Oficina", position = 1)
	private String officeCode;
	@ApiModelProperty(name = "materialCode", value = "Codigo del Material", position = 1)
	private String materialCode;
	@ApiModelProperty(name = "count", value = "Cantidad", position = 1)
	private int count;
}
