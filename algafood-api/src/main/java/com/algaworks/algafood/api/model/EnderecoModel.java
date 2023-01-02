package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {
	
	@ApiModelProperty(example = "00000-000")
	private String cep;	
	
	@ApiModelProperty(example = "Rua Juca Teixeira")
	private String logradouro;	
	
	@ApiModelProperty(example = "264")
	private String numero;	
	
	@ApiModelProperty(example = "Super Pratico Supermercado")
	private String complemento;	
	
	@ApiModelProperty(example = "Senhor Teixeira")
	private String bairro;
	
	private CidadeResumoModel cidade;

}
