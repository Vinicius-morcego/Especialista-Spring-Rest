package com.algaworks.algafood.infrastructure.service.email;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService{

	@Autowired(required = true)
	private JavaMailSender mailSender;
	
	@Autowired(required = true)
	private EmailProperties emailProperties;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			
			var corpo = processarTemplate(mensagem);
			MimeMessage mimeMailMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);
			
			mailSender.send(mimeMailMessage);			
		} catch (Exception e) {
				throw new MailException("Não foi possivel o envio do e-mail", e);
		}
		
	}
	
	public String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new MailException("Não foi possivel montar o template do e-mail", e);
		}
		
		
	}

}
