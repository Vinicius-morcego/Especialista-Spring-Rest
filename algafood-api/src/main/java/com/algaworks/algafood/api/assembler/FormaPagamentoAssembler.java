package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.RestauranteFormaPagamentoController;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.modelo.FormaPagamento;

@Component
public class FormaPagamentoAssembler 
	extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel>{

	public FormaPagamentoAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
		
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		FormaPagamentoModel formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);
		modelMapper.map(formaPagamento, formaPagamentoModel);		
		formaPagamentoModel.add(linkTo(FormaPagamentoController.class).withRel("formas de pagamento"));
		return formaPagamentoModel;
	}
	
	public void copyDomainObject(FormaPagamentoInput formaPagamentoInput, 
			FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamento, formaPagamentoInput);
	}
	

	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {		
		return super.toCollectionModel(entities).add(
				linkTo(FormaPagamentoController.class).withRel("formas de pagamento"));
	}
}
