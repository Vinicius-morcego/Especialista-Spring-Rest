package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {


	@Operation(summary = "Lista os grupos")
	CollectionModel<GrupoModel> listar();
	
	@Operation(summary = "Lista o grupo por ID", description = "Lista um grupo por ID, "
			+ "necessita de um ID válido")
	GrupoModel buscar(Long grupoId);
	
	@Operation(summary = "Cadastro de grupo", description = "Cadastro de um grupo, "
			+ "necessita de uma descrição válida")
	GrupoModel salvar(GrupoInput grupoInput);
	
	@Operation(summary = "Atualiza o grupo por ID", description = "Atualização de um grupo por ID, "
			+ "necessita de um ID válido")
	GrupoModel atualizar(Long grupoId, GrupoInput grupoInput);
	
	@Operation(summary = "Remove o grupo por ID", description = "Remoção de um grupo por ID, "
			+ "necessita de um ID válido")
	void excluir(Long grupoId);
}