package com.algaworks.algafood.api.model.mixin;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.core.validation.TaxaFrete;
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
