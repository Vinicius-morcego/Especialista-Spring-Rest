package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.openapi.controller.StatusPedidoControllerOpenApi;
import com.algaworks.algafood.domain.service.StatusPedidoService;

@RestController
@RequestMapping("/v1/pedidos/{codigoPedido}")
public class StatusPedidoController implements StatusPedidoControllerOpenApi{	
	
	@Autowired
	private StatusPedidoService statusPedidoService;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/confirmar")
	public ResponseEntity<Void> confirmar(@PathVariable String codigoPedido){		
		statusPedidoService.confirmar(codigoPedido);
		return ResponseEntity.noContent().build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/cancelar")
	public ResponseEntity<Void> cancelar(@PathVariable String codigoPedido){		
		statusPedidoService.cancelar(codigoPedido);
		return ResponseEntity.noContent().build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/entregue")
	public ResponseEntity<Void> entregue(@PathVariable String codigoPedido){		
		statusPedidoService.entregue(codigoPedido);
		return ResponseEntity.noContent().build();
	}
}
