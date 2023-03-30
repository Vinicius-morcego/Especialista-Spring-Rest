package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteResponsavelControllerOpenApi {

	public static String MSG_NOT_FOUND = "Restaurante ou usuario não encontrado(s)";
	public static String MSG_BAD_REQUEST = "ID do restaurante ou usuário inválido(s)";

	@Operation(summary = "Lista o usuário responsavel pelo restaurante por ID", 
			description = "Lista um usuário responsavel pelo restaurante por ID, necessita de um ID válido")
	public CollectionModel<UsuarioModel> listar(@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId);	
	
	@Operation(summary = "Desassocia o usuário do restaurante por ID", description = "Desassocia um usuário de um restaurante por ID, "
			+ "necessita de um ID válido")
	public ResponseEntity<Void> desassociar(
			@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "Representa o ID de um usuario responsavel pelo restaurante", example = "1", required = true) Long usuarioId);
	
	@Operation(summary = "Associa o usuário do restaurante por ID", description = "Associa um usuário de um restaurante por ID, "
			+ "necessita de um ID válido")
	public ResponseEntity<Void> associar(@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "Representa o ID de um usuario responsavel pelo restaurante", example = "1", required = true) Long usuarioId);
	
}
