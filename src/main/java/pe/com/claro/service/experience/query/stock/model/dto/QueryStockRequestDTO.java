package pe.com.claro.service.experience.query.stock.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryStockRequestDTO {


  @NotNull(message = "materialCode no debe de ser nulo")
  @NotEmpty(message = "materialCode no debe de ser vacio")
  @ApiModelProperty(name = "materialCode", value = "Código de Material", position = 1)
  private String materialCode; 
  
  @NotNull(message = "code no debe de ser nulo")
  @NotEmpty(message = "code no debe de ser vacio")
  @ApiModelProperty(name = "code", value = "Codigo", position = 2)
  private String code;
  
  @NotNull(message = "typeQuery no debe de ser nulo")
  @NotEmpty(message = "typeQuery no debe de ser vacio")
  @ApiModelProperty(name = "typeQuery", value = "Tipo de consulta", position = 3)
  private String typeQuery;
  
  @NotNull(message = "aplicationCode no debe de ser nulo")
  @NotEmpty(message = "aplicationCode no debe de ser vacio")
  @ApiModelProperty(name = "aplicationCode", value = "Codigo de Aplicacion", position = 4)
  private String aplicationCode;
   
}
