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
		//pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		pedidoModel.add(algaLinks.linkToPedidos("pedidos"));		
		if(algaSecurity.podeGerenciarPedido(pedido.getCodigo())) {
			if(pedido.getStatus().podeAlterarPara(StatusPedido.CONFIRMADO)) {
				pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));			
			}
			if(pedido.getStatus().podeAlterarPara(StatusPedido.CANCELADO)) {
				pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));			
			}		
			if(pedido.getStatus().podeAlterarPara(StatusPedido.ENTREGUE)) {
				pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));			
			}
		}
		
		pedidoModel.getRestaurante().add(algaLinks.linkToRestaurantes(pedidoModel.getRestaurante().getId()));
		
		pedidoModel.getCliente().add(algaLinks.linkToUsuarios(pedidoModel.getCliente().getId()));
		
		pedidoModel.getFormaPagamento().add(algaLinks.linkToFormaPagamentos(pedidoModel.getRestaurante().getId()));
		
		pedidoModel.getEnderecoEntrega().getCidade().add(
				algaLinks.linkToCidades(pedidoModel.getEnderecoEntrega().getCidade().getId()));
		
		pedidoModel.getItens().forEach(item ->{			
				item.getProduto().add(algaLinks.linkToProdutos(pedido.getRestaurante().getId(), "produto"));
		});
		
		return pedidoModel;
	}
	
	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {		
		return super.toCollectionModel(entities).add(linkTo(PedidoController.class).withRel("pedidos"));
	}
}
