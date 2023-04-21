package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoInput {
	
	@Schema(example = "1")
	@NotNull
	private Long id;
	
	@Schema(example = "Cadastrar, Atualizar")
	@NotBlank
	private String nome;
	
	@Schema(example = "Pode Cadastrar um restaurante")
	@NotBlank
	private String descricao;

}
