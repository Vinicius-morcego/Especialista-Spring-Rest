package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	public static String MSG_BAD_REQUEST = "ID do restaurante ou da forma de pagamento inválido(s)";
	public static String MSG_NOT_FOUND = "Restaurante ou forma de pagamento não encontrado(s)";

	@ApiOperation("Lista de formas de pagamento vinculados a um restaurante por ID")	
	@ApiResponses({
		@ApiResponse(code = 405, message = "Recurso não implementado", response = Problem.class)
	})
	public CollectionModel<FormaPagamentoModel> listar(
			@ApiParam(value = "ID de um restaurante") Long restauranteId);
	
	@ApiOperation("Desassocia um restaurante a uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, 
				message = MSG_BAD_REQUEST, 
				response = Problem.class),
		@ApiResponse(code = 404, 
		message = MSG_NOT_FOUND, 
		response = Problem.class)
	})

	public ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true) 
			Long formaPagamentoId);
	
	@ApiOperation("Associa um restaurante a uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, 
				message = MSG_BAD_REQUEST, 
				response = Problem.class),
		@ApiResponse(code = 404, 
		message = MSG_NOT_FOUND, 
		response = Problem.class)
	})
	public ResponseEntity<Void> associar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true) 
			Long formaPagamentoId);
}
