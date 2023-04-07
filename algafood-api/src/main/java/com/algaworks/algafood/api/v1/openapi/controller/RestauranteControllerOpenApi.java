package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
	
	@Operation(summary = "Listagem de restaurantes", parameters = {
		@Parameter(
					name = "projecao", 
					description = "Apenas o nome do restaurante", 
					example = "apenas-nome",
					in = ParameterIn.QUERY,
					required = false
				  )
	}, responses = {
			@ApiResponse(responseCode = "200", description = "Listagem de restaurante realizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
					content = @Content(schema = @Schema(ref = "Problem")))
	})
	
	CollectionModel<RestauranteBasicoModel> listar();
	
	@Operation(summary = "Lista o restaurante por nome "
			+ "necessita de um nome válido", hidden = true, responses = {
					@ApiResponse(responseCode = "200", description = "Listagem de restaurante por nome realizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();
	
	@Operation(summary = "Busca o restaurante por ID "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200", description = "Listagem de restaurante realizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	RestauranteBasicoModel buscar(@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) 
			Long restauranteId);
	
	@Operation(summary = "Cadastro do restaurante", description = "Cadastro de um restaurante, "
			+ "necessita de um nome válido", responses = {
					@ApiResponse(responseCode = "200", description = "Restaurante cadastrado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	RestauranteModel salvar(@RequestBody(description = "Representação de um novo restaurante", required = true) RestauranteInput restauranteInput);
	
	@Operation(summary = "Atualiza um restaurante por ID", description = "Atualização de um restaurante por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	RestauranteModel atualizar(@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) 
		Long restauranteId, @RequestBody(description = "Representação de um restaurante com dados atualizados", required = true)
		RestauranteInput restauranteInput);
	
	@Operation(summary = "Ativa o restaurante por ID", description = "Ativação de um restaurante por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<Void> ativar(Long restauranteId);	
	
	//DELETE /restaurantes/{id}/inativar
	@Operation(summary = "Inativa o restaurante por ID", description = "Inativa um restaurante por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<Void> inativar(Long restauranteId);
	
	@Operation(summary = "Abertura do restaurante por ID "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Abertura de um restaurante realizado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<Void> abrir(@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId);
	
	@Operation(summary = "Ativação em massa de restaurantes", 
			responses = {
					@ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	void ativarMultiplos(@Parameter(description = "Representa uma lista de ID´s de um restaurante", example = "1", required = true) 
			List<Long> restauranteIds);
	
	@Operation(summary = "Inativação em massa de restaurantes", 
			responses = {
					@ApiResponse(responseCode = "204", description = "Restaurantes inativados com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	void inativarMultiplos(List<Long> restauranteIds);
	
	//DELETE /restaurantes/{id}/inativar
	@Operation(summary = "Fecha o restaurante por ID "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Fechamento de restaurante realizado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<Void> fechar(@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId);
	
	@Operation(summary = "Remove o restaurante por ID", description = "Remoção de um restaurante por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Restaurante excluído com sucesso"),
					@ApiResponse(responseCode = "400", description = "Restaurante não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	void excluir(@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId);
}
