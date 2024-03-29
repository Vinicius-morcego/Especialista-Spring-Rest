package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.modelo.Cozinha;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel>{

	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@Override
	public CozinhaModel toModel(Cozinha cozinha) {
		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);
		if(algaSecurity.podeConsultarCozinhas()) {
			cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));			
		}
		
		return cozinhaModel;
	}
	
	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}	
	@Override
	public CollectionModel<CozinhaModel> toCollectionModel(Iterable<? extends Cozinha> entities) {
		var collectionModel = super.toCollectionModel(entities);
		if(algaSecurity.podeConsultarCozinhas()) {
			collectionModel.add(algaLinks.linkToCozinhas("cozinhas"));
		}
		return collectionModel;
	}
}
