package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Fluxo de Pedidos")
public interface StatusPedidoControllerOpenApi {

	public static String MSG_BAD_REQUEST = "ID do pedido inválido";
	public static String MSG_NOT_FOUND = "Pedido não encontrado";

	@Operation(summary = "Confirma o pedido por ID", description = "Confirmação de um pedido por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200", description = "Pedido confirmado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Pedido não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public ResponseEntity<Void> confirmar(
			@Parameter(description = "Representa o ID de um pedido", example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca", required = true) 
			String codigoPedido);
	
	@Operation(summary = "Cancela o pedido por ID", description = "Cancelamento de um pedido por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Pedido não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public ResponseEntity<Void> cancelar(
			@Parameter(description = "Representa o ID de um pedido", example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca", required = true)
			String codigoPedido);	
	
	@Operation(summary = "Entrega do pedido por ID", description = "Entrega de um pedido por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Pedido entregue com sucesso"),
					@ApiResponse(responseCode = "400", description = "Pedido não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public ResponseEntity<Void> entregue(
			@Parameter(description = "Representa o ID de um pedido", example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca", required = true) 
			String codigoPedido);
}
