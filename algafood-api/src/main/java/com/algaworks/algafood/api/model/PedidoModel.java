package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

	private Long id;
	private RestauranteResumoModel restaurante;
	private BigDecimal taxaFrete;
	private BigDecimal total;
	private OffsetDateTime dataCriacao;
	private String status;	
	private UsuarioModel cliente;
	private FormaPagamentoModel formaPagamento;
	private Collection<ItemPedidoModel> itens;
}
