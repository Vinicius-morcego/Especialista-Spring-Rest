package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {

	@ApiParam(example = "Francesa", required = true)
	private String nome;
}
