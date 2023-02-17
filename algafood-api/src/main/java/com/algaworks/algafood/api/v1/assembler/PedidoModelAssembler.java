package com.algaworks.algafood.api.v1.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.modelo.Pedido;
import com.algaworks.algafood.domain.modelo.StatusPedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel>{


	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);		
	}
	
	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getId(), pedido);
		
		modelMapper.map(pedido, pedidoModel);		
		
		 if (algaSecurity.podePesquisarPedidos()) {
		        pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		    }
		    
		    if (algaSecurity.podeGerenciarPedido(pedido.getCodigo())) {
		        if (pedido.podeSerConfirmado()) {
		            pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
		        }
		        
		        if (pedido.podeSerCancelado()) {
		            pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
		        }
		        
		        if (pedido.podeSerEntregue()) {
		            pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
		        }
		    }
		    
		    if (algaSecurity.podeConsultarRestaurantes()) {
		        pedidoModel.getRestaurante().add(
		                algaLinks.linkToRestaurantes(pedido.getRestaurante().getId()));
		    }
		    
		    if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
		        pedidoModel.getCliente().add(
		                algaLinks.linkToUsuarios(pedido.getCliente().getId()));
		    }
		    
		    if (algaSecurity.podeConsultarFormasPagamento()) {
		        pedidoModel.getFormaPagamento().add(
		                algaLinks.linkToFormaPagamentos(pedido.getFormaPagamento().getId()));
		    }
		    
		    if (algaSecurity.podeConsultarCidades()) {
		        pedidoModel.getEnderecoEntrega().getCidade().add(
		                algaLinks.linkToCidades(pedido.getEnderecoEntrega().getCidade().getId()));
		    }
		    
		    // Quem pode consultar restaurantes, também pode consultar os produtos dos restaurantes
		    if (algaSecurity.podeConsultarRestaurantes()) {
		        pedidoModel.getItens().forEach(item -> {
		            item.add(algaLinks.linkToProduto(
		                    pedidoModel.getRestaurante().getId(), item.getProduto().getId(), "produto"));
		        });
		    }	
		return pedidoModel;
	}
	
	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {	
		var collectionModel = super.toCollectionModel(entities);
		if(algaSecurity.podePesquisarPedidos()) {
			collectionModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		}
		return collectionModel;
	}
}
