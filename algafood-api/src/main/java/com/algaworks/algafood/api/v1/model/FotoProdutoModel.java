package com.algaworks.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FotoProdutoModel{

	@ApiModelProperty(example = "prive.jpg")
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Foto do prato de pato no tucupi")
	private String descricao;
	
	private String contentType;
	private Long tamanho;
}
