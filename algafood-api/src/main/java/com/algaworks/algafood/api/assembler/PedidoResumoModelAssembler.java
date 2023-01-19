package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.domain.modelo.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getId(), pedido);		
		modelMapper.map(pedido, pedidoResumoModel);		
		pedidoResumoModel.add(algaLinks.linkToPedidos());
		
		return pedidoResumoModel;
	}
	
	@Override
	public CollectionModel<PedidoResumoModel> toCollectionModel(Iterable<? extends Pedido> entities) {		
		return super.toCollectionModel(entities).add(algaLinks.linkToPedidos());
	}
//	public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos){
//		return pedidos.stream()
//		.map(pedido -> toModel(pedido))
//		.collect(Collectors.toList());
//	}
}
