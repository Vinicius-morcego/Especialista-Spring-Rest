package com.algaworks.algafood.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService{	
	@Override
	public void enviar(Mensagem mensagem) {		
			var corpo = processarTemplate(mensagem);						
			log.info("[Email Fake]", mensagem.getDestinatarios(), corpo);		
	}
}
