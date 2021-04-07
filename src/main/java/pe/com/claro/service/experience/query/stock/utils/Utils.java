package pe.com.claro.service.experience.query.stock.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pe.com.claro.maverick.core.model.HttpHeadersRequest;
import pe.com.claro.service.experience.query.stock.internal.ConsultaStockRequestType;
import pe.com.claro.service.experience.query.stock.internal.InternalRootRequest;
import pe.com.claro.service.experience.query.stock.internal.InternalRootResponse;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockRequestDTO;
import pe.com.claro.service.experience.query.stock.model.dto.QueryStockResponseDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {
	public static Map<String, String> toMap(HttpHeadersRequest header) {
		return new ObjectMapper().convertValue(header, Map.class);
	}

	public static InternalRootRequest parseFromDto(QueryStockRequestDTO obj) {
		return InternalRootRequest.builder().consultaStockRequestType(ConsultaStockRequestType.builder()
				.codigoMaterial(obj.getMaterialCode()).codigo(obj.getCode())
				.tipoConsulta(obj.getTypeQuery()).codigoAplicacion(obj.getAplicationCode())
				.build()).build();
	}

	public static List<QueryStockResponseDTO> parseFromService(InternalRootResponse obj) {
		List<QueryStockResponseDTO> lista= new ArrayList<>();
		obj.getConsultarStockResponseType().getResponseData().getListaStock().stream().forEach(k->lista.add(QueryStockResponseDTO.builder()
				.officeCode(k.getCodigoOficina())
				.materialCode(k.getCodigoMaterial())
				.count(Integer.parseInt(k.getCantidad()))
				.build()));
		return lista;
	}

}
