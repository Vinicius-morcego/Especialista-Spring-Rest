package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@ApiModelProperty(example = "00000-0000")
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "Rua Juca Teixeira")
	@NotBlank
	private String logradouro;	
	
	@ApiModelProperty(example = "264")
	@NotBlank
	private String numero;	
	
	@ApiModelProperty(example = "Super Prático Supermercado")	
	private String complemento;	
	
	@ApiModelProperty(example = "Senhor Teixeira")
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
}
