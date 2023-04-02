package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.modelo.dto.VendaDiaria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatisticas")
public interface EstatisticaControllerOpenApi {
	
	EstatisticasModel estatistica();
	
	@Operation(summary = "Lista as vendas diarias pelo filtro", description = "Lista as vendas diárias pelo filtro informado, "
			+ "necessita de um filtro válido", responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID de cidade inválido",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public List<VendaDiaria> consultarVendasDiarias(@Parameter(description = "Representa os critérios da pesquisa", required = true) VendaDiariaFilter filtro, 
			@Parameter(description = "Representa o tipo do offset", example = "UTC", required = true) String timeOffset);
	
	@Operation(summary = "Lista as vendas diarias pelo filtro e gera em formato PDF", description = "Lista as vendas diárias pelo filtro informado e gera o relatório no formato PDF, "
			+ "necessita de um filtro válido")
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(@Parameter(description = "Representa os critérios da pesquisa", required = true) VendaDiariaFilter filtro,
			@Parameter(description = "Representa o tipo do offset", example = "UTC", required = true) String timeOffset);
}
