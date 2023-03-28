package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel>{

	private Long id;
	private String descricao;
	
}
