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
		
		if(!pedido.getStatus().getDescricao().equals(StatusPedido.CRIADO.getDescricao())) {
			throw new NegocioException(
					String.format("O status do pedido %d não pode ser alterado de %s para %s", 
							pedido.getId(), pedido.getStatus().getDescricao(), 
							StatusPedido.CONFIRMADO.getDescricao()));
		}
		
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
		
	}
}
