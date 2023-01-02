package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@ApiOperation("Lista as cozinhas")
	@ApiResponses({
		@ApiResponse(code = 405, message = "Recurso não implementado")		
	})
	public Page<CozinhaModel> listar(
			@ApiParam(example = "corpo", value = "Página de representação das cozinhas") Pageable pageable);
	
	@ApiOperation("Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha não encontrado", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)		
	})
	
	public CozinhaModel buscar(@ApiParam("ID da cozinha") Long cozinhaId);
	
	@ApiOperation("Cadastra a cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada")
	})	
	public CozinhaModel adicionar(
			@ApiParam(example = "corpo", value = "Representação da cozinha") 
			CozinhaInput cozinhaInput);
	
	@ApiOperation("Atualiza a cozinha")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada"),		
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
		
	})
	public CozinhaModel atualizar(
			@ApiParam(example = "1", value = "ID da cozinha") 
			Long cozinhaId, 
			@ApiParam(example = "corpo", value = "Representação da cozinha com dados novos")
			CozinhaInput cozinhaInput);
	
	@ApiOperation("Remove a cozinha")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha excluída"),		
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
		
	})
	public void remover(@ApiParam("ID da cozinha") Long cozinhaId);
}
