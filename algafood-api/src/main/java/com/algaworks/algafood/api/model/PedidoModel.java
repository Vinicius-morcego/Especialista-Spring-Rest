package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

	@ApiModelProperty(example = "1")
	private String codigo;
	
	@ApiModelProperty(example = "15.2")
	private BigDecimal subTotal;
	
	@ApiModelProperty(example = "2.4")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "20.6")
	private BigDecimal total;
	
	@ApiModelProperty(example = "ENTREGUE")
	private String status;	
	
	@ApiModelProperty(example = "2023-01-02T20:45:13.921583Z")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(example = "2023-01-03T10:05:22.921583Z")
	private OffsetDateTime dataConfirmacao;	
	
	@ApiModelProperty(example = "2023-01-03T13:50:10.921583Z")
	private OffsetDateTime dataEntrega;	
	
	@ApiModelProperty(example = "2023-01-03T20:45:13.921583Z")
	private OffsetDateTime dataCancelamento;	
	
	private RestauranteResumoModel restaurante;	
	
	private UsuarioModel cliente;	
	
	private FormaPagamentoModel formaPagamento;
	
	private EnderecoModel enderecoEntrega;
	
	private List<ItemPedidoModel> itens;
	
}
