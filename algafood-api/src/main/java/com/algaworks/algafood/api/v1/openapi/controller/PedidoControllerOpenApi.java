package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApi {
	
	@Operation(summary = "Lista os pedidos por filtro", description = "Lista os pedidos pelo filtro informado, "
			+ "necessita de um filtro válido")
	PagedModel<PedidoResumoModel> pesquisar(
			@Parameter(description = "Representa os criterios da consulta", example = "1", required = true) PedidoFilter filter,
			@Parameter(description = "Representa os dados de paginação", example = "1", required = true) @PageableDefault(size = 2) Pageable pageable);
	
	@Operation(summary = "Lista o pedido por ID", description = "Lista um pedido por ID, "
			+ "necessita de um ID válido")
	PedidoModel buscar(
			@Parameter(description = "Representa o ID de um pedido", example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca", required = true) 
			String codigoPedido);
	
	@Operation(summary = "Cadastro de pedido", description = "Cadastro de um pedido")
	PedidoModel adicionar(@RequestBody(description = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);

}
