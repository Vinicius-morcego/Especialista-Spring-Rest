package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {


	CollectionModel<GrupoModel> listar();
	GrupoModel buscar(Long grupoId);
	GrupoModel salvar(GrupoInput grupoInput);
	GrupoModel atualizar(Long grupoId, GrupoInput grupoInput);
	void excluir(Long grupoId);
}