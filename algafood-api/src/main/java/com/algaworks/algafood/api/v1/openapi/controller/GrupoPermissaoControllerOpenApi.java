package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public interface GrupoPermissaoControllerOpenApi {

	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId);
	public ResponseEntity<Void> associar(Long grupoId, Long permissaoId);	
	public ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId) ;

}
