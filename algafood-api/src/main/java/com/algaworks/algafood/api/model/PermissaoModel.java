package com.algaworks.algafood.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {
	
	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;
	
	@ApiModelProperty(example = "Administrador")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "Usuário tem permissão de administrador")
	@NotBlank
	private String descricao;

}
