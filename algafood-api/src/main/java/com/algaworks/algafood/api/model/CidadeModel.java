package com.algaworks.algafood.api.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CidadeModel {

	private Long id;
	private String nome;
	private EstadoModel estado;
}
