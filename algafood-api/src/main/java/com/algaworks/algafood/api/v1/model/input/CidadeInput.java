package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

	//private Long id;
	@Schema(example = "Campina Verde")
	@NotBlank
	private String nome;
	private EstadoIdInput estado;
}
