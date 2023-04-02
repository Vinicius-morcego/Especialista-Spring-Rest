package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {


	@Operation(summary = "Lista os grupos")
	CollectionModel<GrupoModel> listar();
	
	@Operation(summary = "Lista o grupo por ID", description = "Lista um grupo por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID de cidade inválido",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	GrupoModel buscar(@Parameter(description = "Representa o ID de um grupo", example = "1", required = true) Long grupoId);
	
	@Operation(summary = "Cadastro de grupo", description = "Cadastro de um grupo, "
			+ "necessita de uma descrição válida")
	GrupoModel salvar(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInput grupoInput);
	
	@Operation(summary = "Atualiza o grupo por ID", description = "Atualização de um grupo por ID, "
			+ "necessita de um ID válido")
	GrupoModel atualizar(@Parameter(description = "Representa o ID de um grupo", example = "1", required = true) Long grupoId, 
			@RequestBody(description = "Representação de um grupo com dados atualizados") GrupoInput grupoInput);
	
	@Operation(summary = "Remove o grupo por ID", description = "Remoção de um grupo por ID, "
			+ "necessita de um ID válido")
	void excluir(@Parameter(description = "Representa o ID de um grupo", example = "1", required = true) Long grupoId);
}