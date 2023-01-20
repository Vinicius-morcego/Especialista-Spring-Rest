package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.modelo.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel>{


	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);		
	}
	
	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getId(), pedido);
		
		modelMapper.map(pedido, pedidoModel);		
		//pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		pedidoModel.add(algaLinks.linkToPedidos());		
		
		pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedidoModel.getCodigo(), "confirmar"));
		
		pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedidoModel.getCodigo(), "cancelar"));
		
		pedidoModel.add(algaLinks.linkToEntregaPedido(pedidoModel.getCodigo(), "entregar"));
		
		pedidoModel.getRestaurante().add(algaLinks.linkToRestaurantes(pedidoModel.getRestaurante().getId()));
		
		pedidoModel.getCliente().add(algaLinks.linkToUsuarios(pedidoModel.getCliente().getId()));
		
		pedidoModel.getFormaPagamento().add(algaLinks.linkToFormaPagamentos(pedidoModel.getRestaurante().getId()));
		
		pedidoModel.getEnderecoEntrega().getCidade().add(
				algaLinks.linkToCidades(pedidoModel.getEnderecoEntrega().getCidade().getId()));
		
		pedidoModel.getItens().forEach(item ->{			
				item.getProduto().add(algaLinks.linkToRestauranteProduto(pedidoModel
				.getRestaurante().getId(), item.getProduto().getId(), "produto"));
		});
		
		return pedidoModel;
	}
	
	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {		
		return super.toCollectionModel(entities).add(linkTo(PedidoController.class).withRel("pedidos"));
	}
}
