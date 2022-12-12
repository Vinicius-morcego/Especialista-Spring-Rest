package com.algaworks.algafood.infrastructure.service.email;

public class MailException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public MailException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailException(String message) {
		super(message);
	}
	
	

}
