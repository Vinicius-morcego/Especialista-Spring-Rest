package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class FormaPagamentoIdInput {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;
}
