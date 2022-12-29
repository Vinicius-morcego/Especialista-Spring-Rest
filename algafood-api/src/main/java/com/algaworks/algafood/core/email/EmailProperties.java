package com.algaworks.algafood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

	@NotNull
	private String remetente;
	
	private Smtp smtp = new Smtp();
	
	private Fake fake = new Fake();
	
	private Sandbox sandbox = new Sandbox();
	
	private Implantacao impl = Implantacao.FAKE;
	
	public enum Implantacao{
		SMTP, FAKE, SANDBOX
	}
	
	@Getter
	@Setter
	public class Fake {
		private String impl;
	}
	
	@Getter
	@Setter
	public class Smtp{
		private String host;
		private String port;
		private String username;
	}
	
	@Getter
	@Setter
	public class Sandbox{
		private String destinatario;
	}
}
