package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.model.input.PermissaoInput;
import com.algaworks.algafood.domain.modelo.Permissao;

@Component
public class PermissaoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Permissao toDomainObject(PermissaoInput permissaoInput) {
		return modelMapper.map(permissaoInput, Permissao.class);
	}
}
