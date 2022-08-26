package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.modelo.Restaurante;

@Component
public class RestauranteModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public RestauranteModel toModel(Restaurante restaurante) {
//		RestauranteModel restauranteModel = new RestauranteModel();
//		CozinhaModel cozinhaModel = new CozinhaModel();
//		cozinhaModel.setId(restaurante.getCozinha().getId());
//		cozinhaModel.setNome(restaurante.getCozinha().getNome());
//		
//		restauranteModel.setId(restaurante.getId());
//		restauranteModel.setNome(restaurante.getNome());
//		restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
//		restauranteModel.setCozinha(cozinhaModel);
//		return restauranteModel;
		return modelMapper.map(restaurante, RestauranteModel.class);
	}
	
	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes){
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
}
