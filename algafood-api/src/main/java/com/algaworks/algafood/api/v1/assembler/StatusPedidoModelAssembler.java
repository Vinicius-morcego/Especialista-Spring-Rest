package com.algaworks.algafood.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.model.StatusPedidoModel;
import com.algaworks.algafood.domain.modelo.Pedido;

@Component
public class StatusPedidoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public StatusPedidoModel toModel(Pedido pedido) {
		return modelMapper.map(pedido, StatusPedidoModel.class);
	}
	
	public List<StatusPedidoModel> toCollectionModel(List<Pedido> pedidos){
		
		return pedidos.stream()
				.map(pedido -> toModel(pedido))
				.collect(Collectors.toList());
	}
}
