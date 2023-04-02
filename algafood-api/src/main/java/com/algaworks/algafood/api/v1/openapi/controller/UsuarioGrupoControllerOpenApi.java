package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

	@Operation(summary = "Lista o grupo de permissão do usuario por ID", description = "Lista um grupo de permissão de um usuario por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID de cidade inválido",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public CollectionModel<GrupoModel>  listar(@Parameter(description = "Representa o ID de um usuario", example = "1", required = true) @PathVariable Long usuarioId);
	
	@Operation(summary = "Desassocia o grupo de permissão do usuario por ID", description = "Desassocia um grupo de permissão de um usuario por ID, "
			+ "necessita de um ID válido")
	public ResponseEntity<Void> desassociar(
			@Parameter(description = "Representa o ID de um usuario", example = "1", required = true) Long usuarioId, 
			@Parameter(description = "Representa o ID de um grupo de permissão de usuario", example = "1", required = true) Long grupoId);
	
	@Operation(summary = "Associa o grupo de permissão do usuario por ID", description = "Associa um grupo de permissão de um usuario por ID, "
			+ "necessita de um ID válido")
	public ResponseEntity<Void> associar(
			@Parameter(description = "Representa o ID de um usuario", example = "1", required = true) Long usuarioId, 
			@Parameter(description = "Representa o ID de um grupo de permissão de usuario", example = "1", required = true) Long grupoId);
}
