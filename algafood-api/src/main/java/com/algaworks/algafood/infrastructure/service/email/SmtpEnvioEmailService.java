package com.algaworks.algafood.infrastructure.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;


public class SmtpEnvioEmailService implements EnvioEmailService{

	@Autowired(required = true)
	private JavaMailSender mailSender;
	
	@Autowired(required = true)
	private EmailProperties emailProperties;
	
	@Autowired
	private ProcessadorEmailTemplate processadorEmailTemplate;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			
			MimeMessage mimeMailMessage = criarMimeMessage(mensagem);
			
			mailSender.send(mimeMailMessage);			
		} catch (Exception e) {
				throw new MailException("Não foi possivel o envio do e-mail", e);
		}
		
	}

	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
		var corpo = processadorEmailTemplate.processarTemplate(mensagem);
		MimeMessage mimeMailMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, "UTF-8");
		helper.setFrom(emailProperties.getRemetente());
		helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
		helper.setSubject(mensagem.getAssunto());
		helper.setText(corpo, true);
		return mimeMailMessage;
	}
	
	
}
