package com.algaworks.algafood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public PermissaoNaoEncontradaException(String mensagem) {
		super(mensagem);	
	}
	
	public PermissaoNaoEncontradaException(String mensagem, Long permissaoId) {
		this(mensagem);	
	}

	
	

}
