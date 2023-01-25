package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.api.v1.model.ProdutoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface ProdutoControllerOpenApi {

	public static String ID_DO_PRODUTO = "ID do produto";
	public static String ID_DO_RESTAURANTE = "ID do restaurante";
	public static String MSG_NOT_FOUND = "Produto do restaurante não encontrado";
	public static String MSG_BAD_REQUEST = "ID do restaurante inválido";

	@ApiOperation("Lista de produtos do restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	public CollectionModel<ProdutoModel> listar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true)
			Long restauranteId,
			@ApiParam(value = "Status do produto", example = "INATIVO")
			Boolean incluirIntativos);	
	
	@ApiOperation("Busca os produtos do restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	@GetMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel buscar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = ID_DO_PRODUTO, example = "1", required = true) 
			Long produtoId);
	
	@ApiOperation("Cadastra um produto do restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto cadastrado")
	})
	public ProdutoModel salvar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId,
			@ApiParam(value = "corpo", example = "Representação de um produto", required = true) 
			ProdutoInput produtoInput);
	
	@ApiOperation("Atualiza os produtos do restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	public ProdutoModel atualizar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = ID_DO_PRODUTO, example = "1", required = true)
			Long produtoId,
			@ApiParam(value = "corpo", example = "Representação de um produto", required = true) 
			ProdutoInput produtoInput);
}
