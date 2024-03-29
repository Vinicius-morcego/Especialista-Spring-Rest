package com.algaworks.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);		
	}
	
	public UsuarioNaoEncontradoException(Long id) {
		this(String.format("Não existe usuário com id %d", id));		
	}


}
