package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {

	@ApiModelProperty(example = "123")
	@NotBlank
	private String senhaAtual;
	
	@ApiModelProperty(example = "321")
	@NotBlank 
	private String novaSenha;
}
