package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.openapi.controller.PermissaoOpenOpenApi;
import com.algaworks.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping("/v1/permissoes")
public class PermissaoController implements PermissaoOpenOpenApi{

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<PermissaoModel> listar(){
		CollectionModel<PermissaoModel> permissoesModel =
				permissaoModelAssembler.toCollectionModel(permissaoRepository.findAll());
		return permissoesModel;
	}
	
}
