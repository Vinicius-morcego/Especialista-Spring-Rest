package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.algaworks.algafood.core.email.EmailProperties;

import jakarta.mail.internet.MimeMessage;

public class SandboxEnvioEmailService extends SmtpEnvioEmailService{	
	
	@Autowired(required = true)
	private EmailProperties emailProperties;
	
	@Override
	protected MimeMessage criarMimeMessage(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = super.criarMimeMessage(mensagem);
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			
			helper.setTo(emailProperties.getSandbox().getDestinatario());
			
			return mimeMessage;
			
		} catch (Exception e) {
			throw new MailException("Não foi possivel enviar o e-mail sandbox", e);
		}
		
	}

}
