package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {
	
	@Schema(example = "Administrativo")
	@NotNull
	private String nome;
}
