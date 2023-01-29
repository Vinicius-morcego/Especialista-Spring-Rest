package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	public static String LISTA_DE_ID_S_DE_RESTAURANTES = "Lista de ID´s de restaurantes";
	public static String LISTA_DE_ID_S_INVÁLIDA = "Lista de ID´s inválida";
	public static String ID_DO_RESTAURANTE = "ID do restaurante";
	public static String MSG_NOT_FOUND = "Restaurante não encontrado";
	public static String MSG_BAD_REQUEST = "ID do restaurante inválido";
	public static String BUSCA_UM_RESTAURANTE_POR_ID = "Busca um restaurante por ID";
	public static String LISTA_RESTAURANTES = "Lista restaurantes";

	@ApiOperation(value = LISTA_RESTAURANTES)
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
				name = "projecao", paramType = "query", type = "string")
	})	
	CollectionModel<RestauranteBasicoModel> listar();
	
	@ApiIgnore
	@ApiOperation(value = LISTA_RESTAURANTES, hidden = true)	
	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();
	
	@ApiOperation(BUSCA_UM_RESTAURANTE_POR_ID)
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
		
	})
	RestauranteBasicoModel buscar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId);	
	
	@ApiOperation("Cadastra um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurante cadastrado")
	})
	RestauranteModel salvar(
			@ApiParam(value = "corpo", example = "Representação de um restaurante", required = true) 
			RestauranteInput restauranteInput);
	
	@ApiOperation("Atualiza o restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	RestauranteModel atualizar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = "corpo", example = "Representação de um restaurante", required = true)
			RestauranteInput restauranteInput);
	
	@ApiOperation("Atualiza o status do restaurante para ativo")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	ResponseEntity<Void> ativar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId);
	
	//DELETE /restaurantes/{id}/inativar
	
	@ApiOperation("Atualiza o status do restaurante para inativo")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	ResponseEntity<Void> inativar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId);
	
	@ApiOperation("Atualiza o status do restaurante para aberto")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
		
	})
	ResponseEntity<Void> abrir(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId);
	
	@ApiOperation("Ativa multiplos restaurantes")
	@ApiResponses({
		@ApiResponse(code = 400, message = LISTA_DE_ID_S_INVÁLIDA, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	void ativarMultiplos(
			@ApiParam(value = LISTA_DE_ID_S_DE_RESTAURANTES, required = true)
			List<Long> restauranteIds);
	
	@ApiOperation("Inativa multiplos restaurantes")
	@ApiResponses({
		@ApiResponse(code = 400, message = LISTA_DE_ID_S_INVÁLIDA, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	void inativarMultiplos(
			@ApiParam(value = LISTA_DE_ID_S_DE_RESTAURANTES, required = true) 
			List<Long> restauranteIds);
	
	//DELETE /restaurantes/{id}/inativar
	
	@ApiOperation("Atualiza o status do restaurante para fechado")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	ResponseEntity<Void> fechar(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId);
	
	@ApiOperation("Exclui um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = MSG_BAD_REQUEST, response = Problem.class),
		@ApiResponse(code = 404, message = MSG_NOT_FOUND, response = Problem.class)
	})
	void excluir(
			@ApiParam(value = ID_DO_RESTAURANTE, example = "1", required = true) 
			Long restauranteId);
}
