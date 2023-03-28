package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;


public interface EstadoControllerOpenApi {

	public static String MSG_NOT_FOUND = "Estado não encontrado";
	public static String MSG_BAD_REQUEST = "ID do estado inválido";	
	CollectionModel<EstadoModel> listar();
		
	EstadoModel buscar(Long estadoId);
	EstadoModel salvar(EstadoInput estadoInput);	
	EstadoModel atualizar(Long estadoId, EstadoInput estadoInput);
	void remover(@PathVariable Long estadoId);
}
