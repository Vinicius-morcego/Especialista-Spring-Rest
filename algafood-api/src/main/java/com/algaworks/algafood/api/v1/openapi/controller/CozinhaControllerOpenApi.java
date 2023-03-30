package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@Operation(summary = "Lista as cozinhas")
	PagedModel<CozinhaModel> listar(Pageable pageable);
	
	@Operation(summary = "Lista a cozinha por ID", description = "Lista uma cozinha por ID, "
			+ "necessita de um ID válido")
	CozinhaModel buscar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);
	
	@Operation(summary = "Cadastro de cozinha", description = "Cadastra uma cozinha, "
			+ "necessita de um nome válido")	
	CozinhaModel adicionar(@RequestBody(description = "Representação de uma nova cozinha", required = true) CozinhaInput cozinhaInput);
	
	@Operation(summary = "Atualiza uma cozinha por ID", description = "Atualização de uma cozinha por ID, "
			+ "necessida de um ID válido")
	CozinhaModel atualizar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId,
			@RequestBody(description = "Representação de uma cozinha com dados atualizados", required = true) CozinhaInput cozinhaInput);
	
	@Operation(summary = "Remove uma cozinha por ID", description = "Remoção de uma cozinha por ID, "
			+ "necessida de um ID válido")
	void remover(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);
}
