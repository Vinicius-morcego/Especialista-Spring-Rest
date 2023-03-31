package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
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
