package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.service.StatusPedidoService;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class StatusPedidoController {	
	
	@Autowired
	private StatusPedidoService statusPedidoService;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/confirmar")
	public void confirmar(@PathVariable Long pedidoId){		
		statusPedidoService.confirmar(pedidoId);
		
	}
	
//	@GetMapping("/{pedidoId}/status")
//	@ResponseStatus(HttpStatus.OK)
//	public List<StatusPedidoModel> listar(@PathVariable Long pedidoId){
//		
//		
//		
//	}

}
