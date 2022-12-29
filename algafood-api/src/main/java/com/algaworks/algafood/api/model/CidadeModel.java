package com.algaworks.algafood.api.model;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
@Component
public class CidadeModel {

	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Campina Verde")
	private String nome;
	private EstadoModel estado;
}
