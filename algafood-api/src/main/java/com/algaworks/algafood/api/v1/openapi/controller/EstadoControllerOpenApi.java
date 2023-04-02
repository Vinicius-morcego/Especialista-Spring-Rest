package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados")
public interface EstadoControllerOpenApi {

	public static String MSG_NOT_FOUND = "Estado não encontrado";
	public static String MSG_BAD_REQUEST = "ID do estado inválido";	
	
	@Operation(summary = "Lista os estados")
	CollectionModel<EstadoModel> listar();
	
	@Operation(summary = "Lista o estado por ID", description = "Lista um estado por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID de cidade inválido",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	EstadoModel buscar(@Parameter(description = "ID de um estado", example = "1", required = true) Long estadoId);
	
	@Operation(summary = "Cadastro de estado", description = "Cadastro de um estado, "
			+ "necessita de um nome válido")
	EstadoModel salvar(@RequestBody(description = "Representação de um novo estado", required = true) EstadoInput estadoInput);	
	
	@Operation(summary = "Atualiza o estado por ID", description = "Atualização de um estado por ID, "
			+ "necessida de um ID válido")
	EstadoModel atualizar(@Parameter(description = "ID de uma estado", example = "1", required = true) Long estadoId, 
			@RequestBody(description = "Representação de um estado com dados atualizados", required = true) EstadoInput estadoInput);
	
	@Operation(summary = "Remove o estado por ID", description = "Remoção de um estado por ID, "
			+ "necessita de um ID válido")
	void remover(@Parameter(description = "ID de um estado", example = "1", required = true) @PathVariable Long estadoId);
}
