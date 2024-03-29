package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteBasicoModel extends RepresentationModel<RestauranteBasicoModel>{

	private Long id;
	
	private String nome;
	
	private boolean aberto;
	
	private boolean ativo;
}
