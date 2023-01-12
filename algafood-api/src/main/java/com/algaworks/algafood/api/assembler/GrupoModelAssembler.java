package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.GrupoController;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.modelo.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoModelAssembler() {
		super(GrupoController.class, GrupoModel.class);
	}
	
	@Override
	public GrupoModel toModel(Grupo grupo) {
		GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
		modelMapper.map(grupo, grupoModel);
		
		grupoModel.add(linkTo(methodOn(GrupoController.class).listar()).withRel("grupos"));
		
		return grupoModel;
	}
	
	public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
		modelMapper.map(grupoInput, grupo);
	}
	
	@Override
	public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {		
		return super.toCollectionModel(entities).add(
				linkTo(methodOn(GrupoController.class).listar()).withRel("grupos")); 
	}
}
