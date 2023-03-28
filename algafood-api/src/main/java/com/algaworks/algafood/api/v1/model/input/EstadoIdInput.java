package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class EstadoIdInput {
	
	@NotNull
	private Long id;
}
