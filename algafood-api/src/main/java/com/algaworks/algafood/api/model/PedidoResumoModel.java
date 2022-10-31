package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Getter;
import lombok.Setter;

@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoModel {

	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal total;
	private String status;	
	private OffsetDateTime dataCriacao;	
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;
	
}