package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class EstadoModel extends RepresentationModel<EstadoModel>{

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Minas Gerais")
	private String nome;
	
}
