package com.algaworks.algafood.api.model.mixin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.modelo.Endereco;
import com.algaworks.algafood.domain.modelo.FormaPagamento;
import com.algaworks.algafood.domain.modelo.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RestauranteMixin {

	@JsonIgnoreProperties(allowGetters = true, value = {"hibernateLazyInitializer","nome"})		
	private Cozinha cozinha;
	
	@JsonIgnore	
	private Endereco endereco;		

	@JsonIgnore		
	private LocalDateTime dataCadastro;
		
	@JsonIgnore		
	private LocalDateTime dataAtualizacao;
		
	@JsonIgnore		
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
		
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();
		
}
