package com.algaworks.algafood.api.openapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
	
	public Page<PedidoResumoModel> pesquisar(PedidoFilter filter,
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
	public PedidoModel buscar(@PathVariable String codigoPedido);
	
	@ApiOperation("Cadatra um pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido cadastrado")
	})
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput);
	
//	private Pageable traduzirPageable(Pageable apiPageable) {
//		
//		var mapeamento = Map.of(
//				
//				"codigo", "codigo",
//				"nomeCliente", "cliente.nome",
//				"restaurante.nome", "restaurante.nome",
//				"valorTotal", "valorTotal"
//				);
//		return PageableTranslator.translate(apiPageable, mapeamento);
//	}

}
