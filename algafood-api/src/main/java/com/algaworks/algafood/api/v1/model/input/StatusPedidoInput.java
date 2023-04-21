package com.algaworks.algafood.api.v1.model.input;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StatusPedidoInput {

	@Schema(example = "Fechado")
	@NotBlank
	private String status;
	
	@Schema(example = "23:53:20")
	@NotBlank
	private OffsetDateTime data;
}
