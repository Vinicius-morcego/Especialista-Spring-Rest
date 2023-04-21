package com.algaworks.algafood.api.v1.model.input;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class RestauranteIdInput {

	@Schema(example = "1")
	@NotNull
	private Long id;
}
