package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.modelo.Restaurante;

@Component
public class RestauranteBasicoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel>{

	@Autowired 
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks; 
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public RestauranteBasicoModelAssembler() {
		super(RestauranteController.class, RestauranteBasicoModel.class);
	}

	@Override
	public RestauranteBasicoModel toModel(Restaurante restaurante) {
		RestauranteBasicoModel restauranteBasicoModel = 
				createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteBasicoModel);
		if(algaSecurity.podeConsultarRestaurantes()) {
			restauranteBasicoModel.add(algaLinks.linkToRestaurantes("restaurantes"));			
		}
		
		if(algaSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
			if(restaurante.getAberto()) {
				restauranteBasicoModel.add(algaLinks.linkToFechandoRestaurante(restaurante.getId(), "fechar"));
			}
			if(!restaurante.getAberto()) {
				restauranteBasicoModel.add(algaLinks.linkToAbrindoRestaurante(restaurante.getId(), "abrir"));
			}
		}
		
		if(algaSecurity.podeGerenciarCadastroRestaurantes()) {
			if(restaurante.getAtivo()) {
				restauranteBasicoModel.add(algaLinks.linkToInativandoRestaurante(restaurante.getId(), "inativar"));
			}
			if(!restaurante.getAtivo()) {
				restauranteBasicoModel.add(algaLinks.linkToAtivandoRestaurante(restaurante.getId(), "ativar"));
			}
			
		}
		if(algaSecurity.gerenciaRestaurante(restaurante.getId())) {			
			restauranteBasicoModel.add(algaLinks.linkToFormaPagamentos(restaurante.getId(), "formas-pagamento"));
			restauranteBasicoModel.add(algaLinks.linkToRestauranteResponsavel(restaurante.getId()));
		}
		return restauranteBasicoModel;
		
	}
	
	@Override
	public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {	
		var collectionModel = super.toCollectionModel(entities);
			if(algaSecurity.podeConsultarRestaurantes()) {
				collectionModel.add(algaLinks.linkToRestaurantes("restaurantes"));
			}
		return collectionModel;
	}

}
