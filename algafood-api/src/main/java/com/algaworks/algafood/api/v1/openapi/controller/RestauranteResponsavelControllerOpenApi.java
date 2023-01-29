package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.UsuarioModel;

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
	public CollectionModel<UsuarioModel> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId);
	
	@ApiOperation("Desassocia um usuario do restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND),
	})
	public ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = "ID do usuario", example = "1", required = true) 
			Long usuarioId);
	
	@ApiOperation("Associa um usuario do restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND),
	})
	public ResponseEntity<Void> associar(
		@ApiParam(value = "ID do restaurante", example = "1", required = true) 
		Long restauranteId, 
		@ApiParam(value = "ID do usuario", example = "1", required = true) 
		Long usuarioId);
	
}
