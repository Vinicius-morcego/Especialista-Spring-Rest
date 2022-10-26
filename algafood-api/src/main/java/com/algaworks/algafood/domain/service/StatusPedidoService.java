package com.algaworks.algafood.domain.service;


import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.modelo.Pedido;
import com.algaworks.algafood.domain.modelo.StatusPedido;



@Service
public class StatusPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Transactional
	public void confirmar(Long pedidoId) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);		
		pedido.confirmar();
	}
	
	@Transactional
	public void cancelar(Long pedidoId) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		pedido.cancelar();		
	}
	
	@Transactional
	public void entregue(Long pedidoId) {		
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		pedido.entregue();
		
	}
}
