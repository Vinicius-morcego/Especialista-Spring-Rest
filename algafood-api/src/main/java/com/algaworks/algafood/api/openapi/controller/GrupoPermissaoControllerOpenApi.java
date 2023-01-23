package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation("Lista os grupos de permissões")
	@ApiResponses({
		@ApiResponse(code = 415, message = "Recurso não implementado", response = Problem.class)
	})
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId);
	
	@ApiOperation("Associa um grupo de permissão por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo ou pemissão inválido(s)", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo ou Permissão não encontrado(s)", response = Problem.class)
	})
	public ResponseEntity<Void> associar(
			@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId, 
			@ApiParam(value = "ID de uma permissão", example = "1", required = true) Long permissaoId);
	
	@ApiOperation("Desassocia um grupo de permissão por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo ou pemissão inválido(s)", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo ou Permissão não encontrado(s)", response = Problem.class)
	})
	public ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId, 
			@ApiParam(value = "ID de uma permissão", example = "1", required = true) Long permissaoId) ;

}
