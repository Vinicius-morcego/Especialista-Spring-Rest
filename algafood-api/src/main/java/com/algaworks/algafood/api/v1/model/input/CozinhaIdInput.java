package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CozinhaIdInput {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;
}