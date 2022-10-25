package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.StatusPedidoInput;
import com.algaworks.algafood.domain.modelo.Pedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class StatusPedidoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Pedido toDomainObject(StatusPedidoInput statusPedidoInput) {		
		return modelMapper.map(statusPedidoInput, Pedido.class);
	}
}
