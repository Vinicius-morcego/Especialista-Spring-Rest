package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId);
	public ResponseEntity<Void> associar(Long grupoId, Long permissaoId);	
	public ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId) ;

}
