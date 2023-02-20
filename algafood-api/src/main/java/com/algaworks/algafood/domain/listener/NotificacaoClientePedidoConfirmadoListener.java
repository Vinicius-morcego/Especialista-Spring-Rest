package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.modelo.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener
	public void aoConfirmarEvento(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();		
		var mensagem = criarMensagemEmail(pedido, " - Pedido confirmado", "emails/pedido-confirmado.html");		
		envioEmail.enviar(mensagem);
	}

	
	public Mensagem criarMensagemEmail(Pedido pedido, String statusPedido, String template) {
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome()+statusPedido)
				.corpo(template)
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		return mensagem;
	}
}
