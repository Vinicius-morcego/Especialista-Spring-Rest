package com.algaworks.algafood.api.v1.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StatusPedidoInput {

	@NotBlank
	private String status;
	
	@NotBlank
	private OffsetDateTime data;
}
