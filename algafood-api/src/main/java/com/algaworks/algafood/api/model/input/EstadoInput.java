package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {
	
	@ApiModelProperty(example = "Minas Gerais")
	private String nome;
	

}
