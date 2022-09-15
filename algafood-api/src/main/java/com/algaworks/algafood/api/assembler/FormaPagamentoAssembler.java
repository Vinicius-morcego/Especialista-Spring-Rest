package com.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.modelo.FormaPagamento;

@Component
public class FormaPagamentoAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
	}
	
	public void copyDomainObject(FormaPagamentoInput formaPagamentoInput, 
			FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamento, formaPagamentoInput);
	}
	
	public List<FormaPagamentoModel> toDomainCollection(Collection<FormaPagamento> formaPagamentos){
		return formaPagamentos
				.stream()
				.map(formaPagamento -> toModel(formaPagamento))
				.collect(Collectors.toList());
	}
}
