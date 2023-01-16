package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.modelo.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel>{

	public ProdutoModelAssembler() {
		super(RestauranteProdutoController.class, ProdutoModel.class);		
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto);
		modelMapper.map(produto, produtoModel);
		produtoModel.add(linkTo(RestauranteProdutoController.class).withRel("produtos"));
		return produtoModel;
	}
	
	public void copyDomainToObject(ProdutoInput produtoInput, Produto produto) {
		modelMapper.map(produtoInput, produto);
	}
	
	@Override
	public CollectionModel<ProdutoModel> toCollectionModel(Iterable<? extends Produto> entities) {		
		return super.toCollectionModel(entities).add(linkTo(RestauranteProdutoController.class).withRel("produtos"));
	}
	
}
