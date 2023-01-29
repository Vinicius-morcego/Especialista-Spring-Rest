package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {
	
	@ApiOperation("Lista os pedidos")
	@ApiResponses({
		@ApiResponse(code = 405, message = "Recurso não implementado", response = Problem.class)
	})
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	
	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filter,
			@PageableDefault(size = 2) Pageable pageable);
	
	
	@ApiOperation("Busca um pedido por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do pedido inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
	})
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	PedidoModel buscar(@ApiParam(
			value = "ID do pedido", example = "1", required = true) String codigoPedido);
	
	@ApiOperation("Cadastra um pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido cadastrado")
	})
	PedidoModel adicionar(
			@ApiParam(value = "corpo", example = "Representação de um pedido", required = true) 
			PedidoInput pedidoInput);

}
