package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Fluxo de Pedidos")
public interface StatusPedidoControllerOpenApi {

	public static String MSG_BAD_REQUEST = "ID do pedido inválido";
	public static String MSG_NOT_FOUND = "Pedido não encontrado";

	public ResponseEntity<Void> confirmar(String codigoPedido);
	
	public ResponseEntity<Void> cancelar(String codigoPedido);	
	public ResponseEntity<Void> entregue(String codigoPedido);
}
