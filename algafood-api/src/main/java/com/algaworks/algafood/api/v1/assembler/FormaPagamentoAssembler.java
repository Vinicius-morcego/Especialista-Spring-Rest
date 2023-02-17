package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.FormaPagamentoController;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.modelo.FormaPagamento;

@Component
public class FormaPagamentoAssembler 
	extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel>{

	public FormaPagamentoAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
		
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@Override
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		FormaPagamentoModel formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);
		modelMapper.map(formaPagamento, formaPagamentoModel);	
		
		if(algaSecurity.podeConsultarFormasPagamento()) {
			formaPagamentoModel.add(algaLinks.linkToFormaPagamentos("formas de pagamento"));			
		}
		
		return formaPagamentoModel;
	}
	
	public void copyDomainObject(FormaPagamentoInput formaPagamentoInput, 
			FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamento, formaPagamentoInput);
	}
	

	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {		
		var collectionModel = super.toCollectionModel(entities);
		if(algaSecurity.podeConsultarFormasPagamento()) {
			collectionModel.add(algaLinks.linkToFormaPagamentos("formas de pagamento"));
		}
		
		return collectionModel;
	}
}
