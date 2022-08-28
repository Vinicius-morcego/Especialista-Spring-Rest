package com.algaworks.algafood.api.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CozinhaModel {
	
	private Long id;
	private String nome;

}
