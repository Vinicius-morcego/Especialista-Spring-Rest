package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

//@JsonFilter("pedidoFilter")
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel>{

	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal total;
	private String status;	
	private OffsetDateTime dataCriacao;
	private RestauranteApenasNomeModel restaurante;
	private UsuarioModel cliente;
	
//	@ApiModelProperty(example = "João da Silva")
//	private String nomeCliente;
	
}
