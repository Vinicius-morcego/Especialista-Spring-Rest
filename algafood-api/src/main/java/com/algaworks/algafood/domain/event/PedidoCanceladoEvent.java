package com.algaworks.algafood.domain.event;

import com.algaworks.algafood.domain.modelo.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent{

	private Pedido pedido; 
}
