package com.algaworks.algafood.api.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface StatusPedidoControllerOpenApi {

	public static String MSG_BAD_REQUEST = "ID do pedido inválido";
	public static String MSG_NOT_FOUND = "Pedido não encontrado";

	@ApiOperation("Confirma o pedido")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	public ResponseEntity<Void> confirmar(
			@ApiParam(value = "ID do pedido", example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca") 
			String codigoPedido);
	
	@ApiOperation("Cancela o pedido")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	public ResponseEntity<Void> cancelar(
			@ApiParam(value = "ID do pedido", example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca") 
			String codigoPedido);	

	@ApiOperation("Atualiza o status do pedido de confirmado para entregue")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	public ResponseEntity<Void> entregue(
			@ApiParam(value = "ID do pedido", example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca") 
			String codigoPedido);
}
