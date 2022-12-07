package com.algaworks.algafood.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.modelo.Pedido;



@Service
public class StatusPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Transactional
	public void confirmar(String codigoPedido) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);		
		pedido.confirmar();
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.cancelar();		
	}
	
	@Transactional
	public void entregue(String codigoPedido) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.entregue();
		
	}
}
