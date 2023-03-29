package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

	public CollectionModel<GrupoModel>  listar(@PathVariable Long usuarioId);	
	public ResponseEntity<Void> desassociar(Long usuarioId, Long grupoId);
	public ResponseEntity<Void> associar(Long usuarioId, Long grupoId);
}
