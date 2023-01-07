package com.algaworks.algafood.api.openapi.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários")
	@ApiResponses({
		@ApiResponse(code = 415, message = "Recurso não implementado", response = Problem.class)
	})
	public Collection<UsuarioModel> listar();
	
	@ApiOperation("Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public UsuarioModel buscar(@PathVariable Long usuarioId);
	 
	@ApiOperation("Cadastra um usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado", response = Problem.class)
	})
	public UsuarioModel salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput);
	
	@ApiOperation("Atualiza um usuário")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public UsuarioModel atualizar(
			@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, 
			@ApiParam(value = "corpo", example = "Representação de um usuário", 
				required = true) UsuarioInput usuarioInput);
	
	@ApiOperation("Atualiza a senha do usuário")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public void atualizarSenha(
			@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, 
			@ApiParam(value = "corpo", example = "Representação de uma senha", required = true) SenhaInput senhaInput);
	
	@ApiOperation("Exclui um usuário")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public void remover(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);
}
