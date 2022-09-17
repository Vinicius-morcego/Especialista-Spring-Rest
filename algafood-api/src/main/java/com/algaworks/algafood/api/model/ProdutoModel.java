package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ProdutoModel {

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
