package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteControllerOpenApi {

	public static String LISTA_DE_ID_S_DE_RESTAURANTES = "Lista de ID´s de restaurantes";
	public static String LISTA_DE_ID_S_INVÁLIDA = "Lista de ID´s inválida";
	public static String ID_DO_RESTAURANTE = "ID do restaurante";
	public static String MSG_NOT_FOUND = "Restaurante não encontrado";
	public static String MSG_BAD_REQUEST = "ID do restaurante inválido";
	public static String BUSCA_UM_RESTAURANTE_POR_ID = "Busca um restaurante por ID";
	public static String LISTA_RESTAURANTES = "Lista restaurantes";
	
	CollectionModel<RestauranteBasicoModel> listar();
	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();
	RestauranteBasicoModel buscar(Long restauranteId);	
	RestauranteModel salvar(RestauranteInput restauranteInput);
	RestauranteModel atualizar(Long restauranteId, RestauranteInput restauranteInput);
	ResponseEntity<Void> ativar(Long restauranteId);	
	
	//DELETE /restaurantes/{id}/inativar
	ResponseEntity<Void> inativar(Long restauranteId);
	ResponseEntity<Void> abrir(Long restauranteId);
	void ativarMultiplos(List<Long> restauranteIds);
	void inativarMultiplos(List<Long> restauranteIds);
	
	//DELETE /restaurantes/{id}/inativar
	ResponseEntity<Void> fechar(Long restauranteId);
	void excluir(Long restauranteId);
}
