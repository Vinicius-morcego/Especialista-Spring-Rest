package com.algaworks.algafood.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
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
	
	private Implantacao impl = Implantacao.FAKE;
	
	public enum Implantacao{
		SMTP, FAKE
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
}
