package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@Operation(summary = "Lista o grupo de permissão por ID", description = "Lista de um grupo de permissão por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200", description = "Listagem de grupo de permissão realizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Grupo de permissão não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public CollectionModel<PermissaoModel> listar(@Parameter(description = "Representa o ID de um grupo de permissão", example = "1", required = true)
		@PathVariable Long grupoId);
	
	@Operation(summary = "Associa o grupo a uma permissão por ID", description = "Associa um grupo a uma permissão por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Associação de grupo de permissão realizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Grupo de permissão não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public ResponseEntity<Void> associar(@Parameter(description = "Representa o ID de um grupo de permissão", example = "1", required = true) Long grupoId, 
			@Parameter(description = "Representa o ID de uma permissão", example = "1", required = true) Long permissaoId);
	
	@Operation(summary = "Desassocia o grupo a uma permissão por ID", description = "Desassocia um grupo a uma permissão por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Desassociação de grupo de permissão realizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Grupo de permissão não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public ResponseEntity<Void> desassociar(@Parameter(description = "Representa o ID de um grupo de permissão", example = "1", required = true) Long grupoId, 
			@Parameter(description = "Representa o ID de uma permissão", example = "1", required = true) Long permissaoId) ;

}
