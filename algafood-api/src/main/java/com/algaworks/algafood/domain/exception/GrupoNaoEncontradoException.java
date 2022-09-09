package com.algaworks.algafood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

	public GrupoNaoEncontradoException(String mensagem) {
		super(mensagem);
		
	}
	
	public GrupoNaoEncontradoException(String mensagem, Long id) {
		this(String.format(mensagem, id));
		
	}
	
	

	private static final long serialVersionUID = 1L;

}
