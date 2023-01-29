package com.algaworks.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhaInput")
@Getter
@Setter
public class CozinhaInputV2 {

	@ApiParam(example = "Francesa", required = true)
	private String nomeCozinha;
}
