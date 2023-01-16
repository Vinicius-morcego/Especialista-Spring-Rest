package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.modelo.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel>{


	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);		
	}
	
	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getId(), pedido);
		
		modelMapper.map(pedido, pedidoModel);		
		pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		
		if(pedidoModel.getCliente() != null)
			pedidoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
					.buscar(pedido.getCliente().getId())).withSelfRel());
		if(pedidoModel.getFormaPagamento() != null)
			pedidoModel.getFormaPagamento().add(linkTo(
					FormaPagamentoController.class, pedido.getFormaPagamento().getId()).withSelfRel());
		if(pedidoModel.getEnderecoEntrega() != null)
			pedidoModel.getEnderecoEntrega().getCidade()
				.add(linkTo(methodOn(CidadeController.class)
				.buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());
		if(pedidoModel.getItens() != null)
		pedidoModel.getItens().forEach(item ->{
			if(item.getProduto() != null)
				item.getProduto().add(linkTo(
						methodOn(RestauranteProdutoController.class)
						.buscar(pedido.getRestaurante().getId(), item.getProduto().getId()))
						.withSelfRel());
		});
		return pedidoModel;
	}
	
	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {		
		return super.toCollectionModel(entities).add(linkTo(PedidoController.class).withRel("pedidos"));
	}
}
