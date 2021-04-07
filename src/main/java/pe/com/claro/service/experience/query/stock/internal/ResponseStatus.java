package pe.com.claro.service.experience.query.stock.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseStatus {
	private String estado;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String ubicacionError;
	private String fecha;
	private String origen;
	private String idTransaccion;
}
