package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.PermissaoModel;


public interface PermissaoOpenOpenApi {
	public CollectionModel<PermissaoModel> listar();
}
