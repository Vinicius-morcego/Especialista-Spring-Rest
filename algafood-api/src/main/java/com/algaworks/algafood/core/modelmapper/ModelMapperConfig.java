package com.algaworks.algafood.core.modelmapper;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.ItemPedidoModel;
import com.algaworks.algafood.domain.modelo.Endereco;
import com.algaworks.algafood.domain.modelo.Grupo;
import com.algaworks.algafood.domain.modelo.ItemPedido;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Grupo.class, GrupoModel.class)
			.addMapping(Grupo::getNome, GrupoModel::setDescricao);
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoModel.class);
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), 
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		return modelMapper;
	}
	

}
