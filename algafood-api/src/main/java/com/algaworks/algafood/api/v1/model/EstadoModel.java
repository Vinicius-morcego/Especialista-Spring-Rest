package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Component
public class EstadoModel extends RepresentationModel<EstadoModel>{

	
	private Long id;
	
	private String nome;
	
}
