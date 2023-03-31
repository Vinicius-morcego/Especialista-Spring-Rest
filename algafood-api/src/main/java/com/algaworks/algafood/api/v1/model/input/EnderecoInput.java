package com.algaworks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@Schema(example = "38270000")
	@NotBlank
	private String cep;	
	@Schema(example = "Avenida")
	@NotBlank
	private String logradouro;
	@Schema(example = "123456")
	@NotBlank
	private String numero;	
	@Schema(example = "Supermercado Teste")
	private String complemento;
	@Schema(example = "Centro")
	@NotBlank
	private String bairro;	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
}
