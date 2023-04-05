package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.core.springdoc.PageableParameter;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApi {
	
	@Operation(
			summary = "Lista os pedidos por filtro", 
			parameters = {
				@Parameter(in = ParameterIn.QUERY, name = "clienteId",
						description = "ID do cliente para filtro da pesquisa",
						example = "1", schema = @Schema(type = "integer")),
				@Parameter(in = ParameterIn.QUERY, name = "restauranteId",
						description = "ID do restaurante para filtro da pesquisa",
						example = "1", schema = @Schema(type = "integer")),
				@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoInicio",
						description = "Data/hora de criação inicial para filtro da pesquisa",
						example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
				@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoFim",
						description = "Data/hora de criação final para filtro da pesquisa",
						example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
			})
	@PageableParameter
	PagedModel<PedidoResumoModel> pesquisar(
			@Parameter(description = "Representa os criterios da consulta", example = "1", required = true) PedidoFilter filter,
			@Parameter(hidden = true) Pageable pageable);
	
	@Operation(summary = "Lista o pedido por ID", description = "Lista um pedido por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "400", description = "ID de pedido inválido",
							content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "404", description = "Pedido não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	PedidoModel buscar(
			@Parameter(description = "Representa o ID de um pedido", example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca", required = true) 
			String codigoPedido);
	
	@Operation(summary = "Cadastro de pedido", description = "Cadastro de um pedido")
	PedidoModel adicionar(@RequestBody(description = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);

}
