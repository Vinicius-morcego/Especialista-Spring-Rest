package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApi {
	
	@Operation(summary = "Lista os pedidos por filtro", description = "Lista os pedidos pelo filtro informado, "
			+ "necessita de um filtro válido")
	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filter,
			@PageableDefault(size = 2) Pageable pageable);
	
	@Operation(summary = "Lista o pedido por ID", description = "Lista um pedido por ID, "
			+ "necessita de um ID válido")
	PedidoModel buscar(String codigoPedido);
	
	@Operation(summary = "Cadastro de pedido", description = "Cadastro de um pedido")
	PedidoModel adicionar(PedidoInput pedidoInput);

}
