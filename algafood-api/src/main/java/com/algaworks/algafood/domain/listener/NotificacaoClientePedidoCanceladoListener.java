package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.modelo.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;

@Component
public class NotificacaoClientePedidoCanceladoListener extends NotificacaoClientePedidoConfirmadoListener{	
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener
	public void aoCancelarPedido(PedidoCanceladoEvent event) {
		Pedido pedido = event.getPedido();
		var mensagem = criarMensagemEmail(pedido, " - Pedido cancelado", "emails/pedido-cancelado.html");
		envioEmail.enviar(mensagem);
		
	}

}
