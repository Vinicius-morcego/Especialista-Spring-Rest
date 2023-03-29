package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;

import io.swagger.v3.oas.annotations.Operation;
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
	
	@Operation(summary = "Lista os restaurantes")
	CollectionModel<RestauranteBasicoModel> listar();
	
	@Operation(summary = "Lista o restaurante por nome", description = "Lista o restaurante pelo nome informado, "
			+ "necessita de um nome válido")
	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();
	
	@Operation(summary = "Lista o restaurante por ID", description = "Lista um restaurante por ID, "
			+ "necessita de um ID válido")
	RestauranteBasicoModel buscar(Long restauranteId);
	
	@Operation(summary = "Cadastro do restaurante", description = "Cadastro de um restaurante, "
			+ "necessita de um nome válido")
	RestauranteModel salvar(RestauranteInput restauranteInput);
	
	@Operation(summary = "Atualiza um restaurante por ID", description = "Atualização de um restaurante por ID, "
			+ "necessita de um ID válido")
	RestauranteModel atualizar(Long restauranteId, RestauranteInput restauranteInput);
	
	@Operation(summary = "Ativa o restaurante por ID", description = "Ativação de um restaurante por ID, "
			+ "necessita de um ID válido")
	ResponseEntity<Void> ativar(Long restauranteId);	
	
	//DELETE /restaurantes/{id}/inativar
	@Operation(summary = "Inativa o restaurante por ID", description = "Inativa um restaurante por ID, "
			+ "necessita de um ID válido")
	ResponseEntity<Void> inativar(Long restauranteId);
	
	@Operation(summary = "Abertura do restaurante por ID", description = "Abertura de um restaurante por ID, "
			+ "necessita de um ID válido")
	ResponseEntity<Void> abrir(Long restauranteId);
	void ativarMultiplos(List<Long> restauranteIds);
	void inativarMultiplos(List<Long> restauranteIds);
	
	//DELETE /restaurantes/{id}/inativar
	@Operation(summary = "Fecha o restaurante por ID", description = "Fechamento de um restaurante por ID, "
			+ "necessita de um ID válido")
	ResponseEntity<Void> fechar(Long restauranteId);
	
	@Operation(summary = "Remove o restaurante por ID", description = "Remoção de um restaurante por ID, "
			+ "necessita de um ID válido")
	void excluir(Long restauranteId);
}
