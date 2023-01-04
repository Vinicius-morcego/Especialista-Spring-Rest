package com.algaworks.algafood.api.openapi.controller;

import java.util.Collection;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteResponsavelControllerOpenApi {

	public static String MSG_NOT_FOUND = "Restaurante ou usuario não encontrado(s)";
	public static String MSG_BAD_REQUEST = "ID do restaurante ou usuário inválido(s)";

	@ApiOperation("Lista os usuarios do restaurante por ID ")
	@ApiResponses({
		@ApiResponse(code = 405, message = "Recurso não implementado", response = Problem.class)
	})
	public Collection<UsuarioModel> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId);
	
	@ApiOperation("Desassocia um usuario do restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND),
	})
	public void desassociar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = "ID do usuario", example = "1", required = true) 
			Long usuarioId);
	
	@ApiOperation("Associa um usuario do restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND),
	})
	public void associar(
		@ApiParam(value = "ID do restaurante", example = "1", required = true) 
		Long restauranteId, 
		@ApiParam(value = "ID do usuario", example = "1", required = true) 
		Long usuarioId);
	
}
