package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	public static String MSG_NOT_FOUND = "Estado não encontrado";
	public static String MSG_BAD_REQUEST = "ID do estado inválido";

	@ApiOperation("Lista os estados")
	@ApiResponses({
		@ApiResponse(code = 405, message = "Recurso não implementado", response = Problem.class)
	})
	List<EstadoModel> listar();
	
	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})	
		EstadoModel buscar(
			@ApiParam(value = "ID do estado", example = "1", required = true)
			Long estadoId);
	
	
	@ApiOperation("Cadastra um estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado")		
	})
		EstadoModel salvar(
			@ApiParam(value = "corpo", example = "Representação de um estado", required = true) 
			EstadoInput estadoInput);
	
	@ApiOperation("Atualiza um estado")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
		EstadoModel atualizar(
			@ApiParam(value = "ID do estado", example = "1", required = true) 
			Long estadoId, 
			@ApiParam(value = "corpo", example = "Representação de um estado", required = true)
			EstadoInput estadoInput);
	
	@ApiOperation("Remove um estado")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
		void remover(@PathVariable Long estadoId);
}
