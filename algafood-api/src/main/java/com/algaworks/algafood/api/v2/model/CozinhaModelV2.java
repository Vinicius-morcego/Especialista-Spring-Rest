package com.algaworks.algafood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
@Component
public class CozinhaModelV2 extends RepresentationModel<CozinhaModelV2>{
	
	
	//@JsonView(RestauranteView.Resumo.class)
	private Long idCozinha;
	//@JsonView(RestauranteView.Resumo.class)
	private String nomeCozinha;

}
