package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.PermissaoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissoes")
public interface PermissaoOpenOpenApi {
	
	@Operation(summary = "Lista as permissões", responses = {
			@ApiResponse(responseCode = "200", description = "Listagem de permissões realizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Permissão não encontrada",
					content = @Content(schema = @Schema(ref = "Problem")))
	})
	public CollectionModel<PermissaoModel> listar();
}
