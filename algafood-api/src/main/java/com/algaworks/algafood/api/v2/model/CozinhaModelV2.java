package com.algaworks.algafood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.domain.modelo.Cozinha;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhaModel")
@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
@Component
public class CozinhaModelV2 extends RepresentationModel<CozinhaModelV2>{
	
	@ApiModelProperty(example = "1")
	//@JsonView(RestauranteView.Resumo.class)
	private Long idCozinha;
	@ApiModelProperty(example = "Francesa")
	//@JsonView(RestauranteView.Resumo.class)
	private String nomeCozinha;

}
