package com.algaworks.algafood.api.model.input;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CidadeIdInput {

	@ApiModelProperty(example = "1")
	private Long id;
}
