package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")

@Getter
@Setter
@Component
public class CidadeModel extends RepresentationModel<CidadeModel>{

	private Long id;
	private String nome;
	private EstadoModel estado;
}
