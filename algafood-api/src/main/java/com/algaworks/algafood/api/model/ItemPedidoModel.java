package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "1")
	private Long produtoId;
	
	@ApiModelProperty(example = "Costela assada no bafo")
	private String produtoNome;
	
	@ApiModelProperty(example = "5")
	private Integer quantidade;
	
	@ApiModelProperty(example = "20.8")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "100")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Servido ao ponto com molho madeira")
	private String observacao;
	
}
