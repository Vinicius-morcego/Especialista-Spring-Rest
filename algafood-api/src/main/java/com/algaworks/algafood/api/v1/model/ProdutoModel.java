package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
@Component
public class ProdutoModel extends RepresentationModel<ProdutoModel>{

	
	@NotNull
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotNull
	private BigDecimal preco;
	@NotNull
	private Boolean ativo;
	
}
