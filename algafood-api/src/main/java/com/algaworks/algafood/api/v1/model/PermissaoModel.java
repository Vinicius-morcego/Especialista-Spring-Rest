package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissões")
@Getter
@Setter
public class PermissaoModel extends RepresentationModel<PermissaoModel>{
	
	
	@NotNull
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

}
