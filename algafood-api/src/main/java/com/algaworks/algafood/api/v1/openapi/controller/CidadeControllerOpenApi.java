package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades")
	CollectionModel<CidadeModel> listar();
	
	@Operation(summary = "Lista a cidade por ID", description = "Lista uma cidade por ID,"
			+ "necessita de um ID válido")
	CidadeModel buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);
	
	@Operation(summary = "Cadastra a cidade", description = "Cadastro de uma cidade,"
			+ "necessita de um nome válido")
	CidadeModel salvar(@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);
	
	@Operation(summary = "Atualiza a cidade por ID", description = "Atualização uma cidade por ID,"
			+ "necessita de um ID válido")
	CidadeModel atualizar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId, 
			@RequestBody(description = "Representação de uma cidade com dados atualizados", required = true) CidadeInput cidadeInput);
	
	@Operation(summary = "Remove a cidade por ID", description = "Remoção de uma cidade por ID,"
			+ "necessita de um ID válido")
	void remover(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);
		
}
