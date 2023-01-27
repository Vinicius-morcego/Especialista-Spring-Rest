package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.input.ItemPedidoInput;
import com.algaworks.algafood.api.v1.model.EnderecoModel;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.modelo.Cidade;
import com.algaworks.algafood.domain.modelo.Endereco;
import com.algaworks.algafood.domain.modelo.Grupo;
import com.algaworks.algafood.domain.modelo.ItemPedido;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
		.addMappings(mapper -> mapper.skip(Cidade::setId));		
		
		modelMapper.createTypeMap(Grupo.class, GrupoModel.class)
			.addMapping(Grupo::getNome, GrupoModel::setDescricao);
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoModel.class);
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), 
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		return modelMapper;
	}
	

}
