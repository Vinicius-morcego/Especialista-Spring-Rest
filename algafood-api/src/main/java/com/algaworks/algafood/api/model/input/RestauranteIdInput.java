package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class RestauranteIdInput {

	@NotNull
	private Long id;
}
