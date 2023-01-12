package com.algaworks.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.modelo.Cidade;
import com.algaworks.algafood.domain.modelo.Estado;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel>{


	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}
	
	@Override
	public CidadeModel toModel(Cidade cidade) {
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		//CidadeModel cidadeModel = modelMapper.map(cidade, CidadeModel.class); 
		modelMapper.map(cidade, cidadeModel);
		//		cidadeModel.add(linkTo(CidadeController.class)
		//		.slash(cidadeModel.getId()).withSelfRel());
//		cidadeModel.add(linkTo(methodOn(CidadeController.class, cidadeModel.getId())
//				.buscar(cidadeModel.getId())).withSelfRel());
		
		//cidadeModel.add(linkTo(CidadeController.class)
		//		.withRel("cidades"));
		
		cidadeModel.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));
		
		//cidadeModel.getEstado().add(linkTo(EstadoController.class)
		//		.slash(cidadeModel.getEstado().getId()).withSelfRel());
//		cidadeModel.getEstado().add(linkTo(methodOn(EstadoController.class)
//				.buscar(cidadeModel.getEstado().getId())).withSelfRel());

		return cidadeModel;
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {	
		return super.toCollectionModel(entities).add(
				linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));
	}
}
