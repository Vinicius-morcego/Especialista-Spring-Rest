package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.modelo.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel>{
	

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);	
	}

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteModel);
		 if (algaSecurity.podeConsultarRestaurantes()) {
			 restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));			 
			 restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "restaurante-produtos"));		    	
		 }    
		    if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
		    	restauranteModel.add(algaLinks.linkToRestauranteResponsavel(
		    			restaurante.getId(), "restaurante-responsavel"));		    	
		    }
			  if (algaSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
				  if(restaurante.getAberto()) {
					  restauranteModel.add(algaLinks.linkToFechandoRestaurante(restaurante.getId(), "fechar"));
				  }
				  if(!restaurante.getAberto()) {
					  restauranteModel.add(algaLinks.linkToAbrindoRestaurante(restaurante.getId(), "abrir"));
				  }				  
			  }
			 if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
				 if(restaurante.getAtivo()) {
					 restauranteModel.add(algaLinks.linkToInativandoRestaurante(restaurante.getId(), "inativar"));
				 }
				 if(!restaurante.getAtivo()) {
					 restauranteModel.add(algaLinks.linkToAtivandoRestaurante(restaurante.getId(), "ativar"));
				 }				 
			 }

		return restauranteModel;
	}
	
	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		CollectionModel<RestauranteModel> collectionModel = super.toCollectionModel(entities);
	    
	    if (algaSecurity.podeConsultarRestaurantes()) {
	        collectionModel.add(algaLinks.linkToRestaurantes("restaurantes"));
	    }
	    
	    return collectionModel;
	}
}
