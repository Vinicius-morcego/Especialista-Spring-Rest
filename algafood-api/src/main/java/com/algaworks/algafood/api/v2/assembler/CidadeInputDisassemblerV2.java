package com.algaworks.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.modelo.Cidade;

@Component
public class CidadeInputDisassemblerV2 {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInputV2 cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
}
