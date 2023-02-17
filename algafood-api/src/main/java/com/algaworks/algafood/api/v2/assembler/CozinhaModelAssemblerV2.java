package com.algaworks.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v2.AlgaLinksV2;
import com.algaworks.algafood.api.v2.controller.CozinhaControllerV2;
import com.algaworks.algafood.api.v2.model.CozinhaModelV2;
import com.algaworks.algafood.api.v2.model.input.CozinhaInputV2;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.modelo.Cozinha;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Component
public class CozinhaModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelV2>{

	public CozinhaModelAssemblerV2() {
		super(CozinhaControllerV2.class, CozinhaModelV2.class);		
	}

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinksV2 algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	@Override
	public CozinhaModelV2 toModel(Cozinha cozinha) {
		CozinhaModelV2 cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);
		if(algaSecurity.podeConsultarCozinhas()) {
			cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));			
		}
		
		return cozinhaModel;
	}
	
	public void copyToDomainObject(CozinhaInputV2 cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}	
	@Override
	public CollectionModel<CozinhaModelV2> toCollectionModel(Iterable<? extends Cozinha> entities) {
		var collectionModel = super.toCollectionModel(entities);
		if(algaSecurity.podeConsultarCozinhas()) {
			collectionModel.add(algaLinks.linkToCozinhas("cozinhas"));
		}
		
		return collectionModel;
	}
}
