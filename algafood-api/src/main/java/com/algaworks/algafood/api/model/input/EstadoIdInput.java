package com.algaworks.algafood.api.model.input;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class EstadoIdInput {

	@ApiModelProperty(value = "ID do estado", example = "1")
	private Long id;
}
