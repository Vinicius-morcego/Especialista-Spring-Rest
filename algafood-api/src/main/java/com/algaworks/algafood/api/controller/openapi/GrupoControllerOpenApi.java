package com.algaworks.algafood.api.controller.openapi;

import java.util.Collection;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os grupos")
	public Collection<GrupoModel> listar();
	
	@ApiOperation("Busca um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID não encontrado", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "1")
		Long grupoId);
	
	@ApiOperation("Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado")
	})
	public GrupoModel salvar(
			@ApiParam(value = "corpo", example = "Representação de um grupo") 
			GrupoInput grupoInput);
	
	@ApiOperation("Atualiza o grupo")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado"),		
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoModel atualizar(
			@ApiParam(value = "ID de um grupo", example = "1") 
			Long grupoId, 
			@ApiParam(value = "corpo", example = "Representação de grupo com novos dados")
			GrupoInput grupoInput);
	
	@ApiOperation("Remove o grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo excluído"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public void excluir(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);
}