package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {
	
//	@ApiModelProperty(example = "1", required = true)
//	private Long id;
	
	@ApiModelProperty(example = "Minas Gerais", required = true)
	private String nome;
	

}
