package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Relation(collectionRelation = "endereços")
@Getter
@Setter
public class EnderecoModel extends RepresentationModel<EnderecoModel>{
	
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
