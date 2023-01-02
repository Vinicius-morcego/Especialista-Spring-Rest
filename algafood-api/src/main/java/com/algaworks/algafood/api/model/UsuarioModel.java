package com.algaworks.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "João da Silva")
	private String nome;
	
	@ApiModelProperty(example = "joao@naoresponda.gmail.com")
	private String email;
	
	@ApiModelProperty(example = "bdd46d58-03fa-49c7-8484-80f6d62c56ca")
	@JsonIgnore
	private String senha;
}
