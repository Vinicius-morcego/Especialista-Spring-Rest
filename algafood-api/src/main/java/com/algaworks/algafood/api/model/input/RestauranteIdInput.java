package com.algaworks.algafood.api.model.input;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class RestauranteIdInput {

	@NotNull
	private Long id;
}
