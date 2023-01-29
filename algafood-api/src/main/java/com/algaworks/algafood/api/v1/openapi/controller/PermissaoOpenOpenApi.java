package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Permissões")
public interface PermissaoOpenOpenApi {

	@ApiOperation("Lista as permissões da API")
	@ApiResponses({
		@ApiResponse(code = 415, message = "Recurso não implementado", response = Problem.class)
	})
	public CollectionModel<PermissaoModel> listar();
}
