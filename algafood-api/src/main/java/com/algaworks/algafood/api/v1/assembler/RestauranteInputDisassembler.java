package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.model.input.RestauranteInput;
import com.algaworks.algafood.domain.modelo.Cidade;
import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.modelo.Restaurante;

@Component
public class RestauranteInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		
		return modelMapper.map(restauranteInput, Restaurante.class);
	}

	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		//para evitar Caused by: org.hibernate.HibernateException: identifier of an instance 
		//of com.algaworks.algafood.domain.modelo.Restaurante was altered from 2 to null
		restaurante.setCozinha(new Cozinha());
		
		if(restaurante.getEndereco() != null)
			restaurante.getEndereco().setCidade(new Cidade());
		modelMapper.map(restauranteInput, restaurante);
	}
}
