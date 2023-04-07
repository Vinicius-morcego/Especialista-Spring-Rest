package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.modelo.dto.VendaDiaria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatisticas")
public interface EstatisticaControllerOpenApi {
	
	@Operation(hidden = true)
	EstatisticasModel estatistica();
	
	@Operation(summary = "Consulta estatisticas de vendas diarias", 
			parameters = {
					@Parameter(in = ParameterIn.QUERY, name = "restauranteId", description = "ID do restaurante", 
						example = "1", schema = @Schema(type = "integer")),
					@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoInicio", description = "Data/hora inicial de criação do pedido", 
						example = "2023-04-07T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
					@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoFim", description = "Data/hora final de criação do pedido", 
					example = "2023-04-07T0023:59:59Z", schema = @Schema(type = "string", format = "date-time"))
	}, responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VendaDiaria.class))),
					@Content(mediaType = "application/pdf", schema = @Schema(type = "string", format = "binary"))
			})
	})
	public List<VendaDiaria> consultarVendasDiarias(@Parameter(hidden = true) VendaDiariaFilter filtro, 
			@Parameter(description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", 
			schema = @Schema(type = "string", defaultValue = "+00:00")) String timeOffset);
	
	@Operation(hidden = true)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(@Parameter(description = "Representa os critérios da pesquisa", required = true) VendaDiariaFilter filtro,
			@Parameter(description = "Representa o tipo do offset", example = "UTC", required = true) String timeOffset);
}
