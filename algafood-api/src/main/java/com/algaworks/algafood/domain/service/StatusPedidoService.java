package com.algaworks.algafood.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.modelo.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;



@Service
public class StatusPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmar(String codigoPedido) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);		
		pedido.confirmar();		
		
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.cancelar();		
		
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void entregue(String codigoPedido) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.entregue();
		
	}
}
