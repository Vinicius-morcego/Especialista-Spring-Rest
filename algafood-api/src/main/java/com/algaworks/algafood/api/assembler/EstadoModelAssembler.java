package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.modelo.Estado;


@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);	
	}
	
	@Override
	public EstadoModel toModel(Estado estado) {
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
		modelMapper.map(estado, estadoModel);
		estadoModel.add(linkTo(methodOn(EstadoController.class).listar()).withRel("estados"));
		return estadoModel;
	}
	
	public void copyDomainObject(EstadoInput estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
	
	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {	
		return super.toCollectionModel(entities)
				.add(linkTo(methodOn(EstadoController.class)
						.listar()).withRel("estados"));
	}	
}
