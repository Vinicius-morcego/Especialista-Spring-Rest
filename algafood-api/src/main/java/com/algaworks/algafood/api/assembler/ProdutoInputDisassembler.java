package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.modelo.Produto;

@Component
public class ProdutoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Produto toObjectDomain(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}
}
