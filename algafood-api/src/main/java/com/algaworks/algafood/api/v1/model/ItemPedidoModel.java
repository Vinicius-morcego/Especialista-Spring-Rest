package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "items")
@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel>{

	
	private Long id;
	
//	@ApiModelProperty(example = "1")
//	private Long produtoId;
//	
//	@ApiModelProperty(example = "Costela assada no bafo")
//	private String produtoNome;
	
	private ProdutoModel produto;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
}
