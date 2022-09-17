package com.algaworks.algafood.domain.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private String nome;	
	
	private String descricao;	
	
	private BigDecimal preco;	
	
	private Boolean ativo = Boolean.TRUE;	

	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
}
