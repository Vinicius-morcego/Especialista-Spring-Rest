package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
@Component
public class CidadeModel extends RepresentationModel<CidadeModel>{

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Campina Verde")
	private String nome;
	private EstadoModel estado;
}
