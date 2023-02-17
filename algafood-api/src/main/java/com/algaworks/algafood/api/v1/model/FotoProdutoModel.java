package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.algaworks.algafood.domain.modelo.FotoProduto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel>{

	
	@ApiModelProperty(example = "prive.jpg")
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Foto do prato de pato no tucupi")
	private String descricao;
	
	private String contentType;
	private Long tamanho;
}
