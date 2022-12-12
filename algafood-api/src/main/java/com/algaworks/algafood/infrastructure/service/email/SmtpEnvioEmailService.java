package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMailMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(mensagem.getCorpo(), true);
			
			mailSender.send(mimeMailMessage);			
		} catch (Exception e) {
				throw new MailException("Não foi possivel o envio do e-mail", e);
		}
		
	}

}
