package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public interface UsuarioGrupoControllerOpenApi {

	public CollectionModel<GrupoModel>  listar(@PathVariable Long usuarioId);
	
	public ResponseEntity<Void> desassociar(Long usuarioId, Long grupoId);
	public ResponseEntity<Void> associar(Long usuarioId, Long grupoId);
}
