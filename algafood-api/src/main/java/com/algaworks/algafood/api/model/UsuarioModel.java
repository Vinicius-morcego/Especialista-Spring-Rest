package com.algaworks.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

	
	private Long id;
	
	private String nome;
	
	private String email;
	
	@JsonIgnore
	private String senha;
}
