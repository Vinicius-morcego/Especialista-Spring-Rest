package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CozinhaIdInput {

	@Schema(example = "1")
	@NotNull
	private Long id;
}
