package com.algaworks.algafood.api.model;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CozinhaModel {
	
	@ApiModelProperty(example = "1")
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	@ApiModelProperty(example = "Francesa")
	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
