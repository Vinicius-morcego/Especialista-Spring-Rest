package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@Operation(summary = "Lista o grupo de permissão por ID", description = "Lista de um grupo de permissão por ID, "
			+ "necessita de um ID válido")
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId);
	
	@Operation(summary = "Associa o grupo a uma permissão por ID", description = "Associa um grupo a uma permissão por ID, "
			+ "necessita de um ID válido")
	public ResponseEntity<Void> associar(Long grupoId, Long permissaoId);
	
	@Operation(summary = "Desassocia o grupo a uma permissão por ID", description = "Desassocia um grupo a uma permissão por ID, "
			+ "necessita de um ID válido")
	public ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId) ;

}
