package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {
	
//	private Long id;
	@Schema(example = "Minas Gerais")
	private String nome;
	

}
