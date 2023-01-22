package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "fotos")
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
