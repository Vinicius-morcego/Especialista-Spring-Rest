package com.algaworks.algafood.api.openapi.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation("Lista os grupos do usuario por ID")
	@ApiResponses({
		@ApiResponse(code = 415, message = "Recurso não implementado", response = Problem.class)		
	})
	public Collection<GrupoModel>  listar(@PathVariable Long usuarioId);
	
	@ApiOperation("Desassocia um usuário a um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuario ou grupo inválido(s)", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuario ou grupo não encontrado(s)", response = Problem.class)
	})
	public void desassociar(
			@ApiParam(value = "ID de um usuario", example = "1", required = true) Long usuarioId, 
			@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);
	
	@ApiOperation("Associa um usuário a um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuario ou grupo inválido(s)", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuario ou grupo não encontrado(s)", response = Problem.class)
	})
	public void associar(
			@ApiParam(value = "ID de um usuario", example = "1", required = true) Long usuarioId, 
			@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);
}
