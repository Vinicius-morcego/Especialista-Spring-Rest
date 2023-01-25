package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "items")
@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel>{

	@ApiModelProperty(example = "1")
	private Long id;
	
//	@ApiModelProperty(example = "1")
//	private Long produtoId;
//	
//	@ApiModelProperty(example = "Costela assada no bafo")
//	private String produtoNome;
	
	private ProdutoModel produto;
	
	@ApiModelProperty(example = "5")
	private Integer quantidade;
	
	@ApiModelProperty(example = "20.8")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "100")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Servido ao ponto com molho madeira")
	private String observacao;
	
}
