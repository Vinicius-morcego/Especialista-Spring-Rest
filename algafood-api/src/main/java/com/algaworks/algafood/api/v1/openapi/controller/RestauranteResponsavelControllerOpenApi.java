package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.UsuarioModel;


public interface RestauranteResponsavelControllerOpenApi {

	public static String MSG_NOT_FOUND = "Restaurante ou usuario não encontrado(s)";
	public static String MSG_BAD_REQUEST = "ID do restaurante ou usuário inválido(s)";

	public CollectionModel<UsuarioModel> listar(Long restauranteId);	
	public ResponseEntity<Void> desassociar(Long restauranteId, Long usuarioId);	
	public ResponseEntity<Void> associar(Long restauranteId, Long usuarioId);
	
}
