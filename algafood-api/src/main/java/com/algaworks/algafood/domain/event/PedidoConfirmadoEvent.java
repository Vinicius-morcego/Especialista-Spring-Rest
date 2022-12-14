package com.algaworks.algafood.domain.event;

import com.algaworks.algafood.domain.modelo.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PedidoConfirmadoEvent {

	private Pedido pedido;
}
