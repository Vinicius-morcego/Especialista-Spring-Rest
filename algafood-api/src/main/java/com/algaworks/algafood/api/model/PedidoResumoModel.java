package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoModel {

	@ApiModelProperty(example = "1")
	private String codigo;
	
	@ApiModelProperty(example = "10.9")
	private BigDecimal subTotal;
	
	@ApiModelProperty(example = "3.0")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "20.9")
	private BigDecimal total;
	
	@ApiModelProperty(example = "CANCELADO")
	private String status;	
	
	@ApiModelProperty(example = "2023-01-03T20:45:13.921583Z")
	private OffsetDateTime dataCriacao;	
	
	
	private RestauranteResumoModel restaurante;
	//private UsuarioModel cliente;
	
	@ApiModelProperty(example = "João da Silva")
	private String nomeCliente;
	
}
