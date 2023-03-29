package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;

import io.swagger.v3.oas.annotations.Operation;
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
			+ "necessita de um ID válido")
	EstadoModel buscar(Long estadoId);
	
	@Operation(summary = "Cadastro de estado", description = "Cadastro de um estado, "
			+ "necessita de um nome válido")
	EstadoModel salvar(EstadoInput estadoInput);	
	
	@Operation(summary = "Atualiza o estado por ID", description = "Atualização de um estado por ID, "
			+ "necessida de um ID válido")
	EstadoModel atualizar(Long estadoId, EstadoInput estadoInput);
	
	@Operation(summary = "Remove o estado por ID", description = "Remoção de um estado por ID, "
			+ "necessita de um ID válido")
	void remover(@PathVariable Long estadoId);
}
