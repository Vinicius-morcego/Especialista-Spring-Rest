package com.algaworks.algafood.api.v2.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v2.model.CozinhaModelV2;
import com.algaworks.algafood.api.v2.model.input.CozinhaInputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerV2OpenApi {

	@ApiOperation("Lista as cozinhas")
	@ApiResponses({
		@ApiResponse(code = 405, message = "Recurso não implementado")		
	})
	PagedModel<CozinhaModelV2> listar(
			@ApiParam(example = "corpo", value = "Página de representação das cozinhas") Pageable pageable);
	
	@ApiOperation("Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha não encontrado", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)		
	})
	
	CozinhaModelV2 buscar(@ApiParam(value = "ID da cozinha", required = true) Long cozinhaId);
	
	@ApiOperation("Cadastra a cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada")
	})	
	CozinhaModelV2 adicionar(
			@ApiParam(example = "corpo", value = "Representação da cozinha", required = true) 
			CozinhaInputV2 cozinhaInput);
	
	@ApiOperation("Atualiza a cozinha")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada"),		
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
		
	})
	CozinhaModelV2 atualizar(
			@ApiParam(example = "1", value = "ID da cozinha", required = true) 
			Long cozinhaId, 
			@ApiParam(example = "corpo", value = "Representação da cozinha com dados novos", required = true)
			CozinhaInputV2 cozinhaInput);
	
	@ApiOperation("Remove a cozinha")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha excluída"),		
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
		
	})
	void remover(@ApiParam(value = "ID da cozinha", required = true) Long cozinhaId);
}
