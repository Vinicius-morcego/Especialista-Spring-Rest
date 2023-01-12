package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
@Component
public class EstadoModel extends RepresentationModel<EstadoModel>{

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Minas Gerais")
	private String nome;
	
}
