package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
@Component
public class CozinhaModel extends RepresentationModel<CozinhaModel>{
	
	
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
