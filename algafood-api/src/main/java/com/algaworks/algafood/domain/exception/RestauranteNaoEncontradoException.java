package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	} 
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe restaurante com o código %d", restauranteId));
	}
	
	
	
	
}
