package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algaworks.algafood.domain.modelo.Restaurante;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formas de pagamento")
@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel>{

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Cartão de crédito", required = true)
	private String descricao;
	
}