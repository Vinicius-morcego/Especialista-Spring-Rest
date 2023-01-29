package com.algaworks.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v2.model.input.CozinhaInputV2;
import com.algaworks.algafood.domain.modelo.Cozinha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CozinhaInputDisassemblerV2 {

	@Autowired
	ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaInputV2 cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}
}
