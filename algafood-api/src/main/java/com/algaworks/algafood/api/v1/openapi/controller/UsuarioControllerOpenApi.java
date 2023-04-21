package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;

import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuarios")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuarios")
	public CollectionModel<UsuarioModel> listar();
	
	@Operation(summary = "Lista o usuario por ID", description = "Lista um usuario por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200", description = "Listagem de usuarios realizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Usuário não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public UsuarioModel buscar(
			@Parameter(description = "Representa o ID de um usuario", example = "1", required = true)
			@PathVariable Long usuarioId);	 
	
	@Operation(summary = "Cadastro do usuario", description = "Cadastro de um usuario, "
			+ "necessita de um login e uma senha válidos", responses = {
					@ApiResponse(responseCode = "200", description = "Usuario cadastrado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Usuário não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public UsuarioModel salvar(@RequestBody(description = "Representação de um novo usuario") 
		@Valid UsuarioComSenhaInput usuarioInput);	
	
	@Operation(summary = "Atualiza o usuario por ID", description = "Atualização de um usuario por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso"),
					@ApiResponse(responseCode = "400", description = "Usuário não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public UsuarioModel atualizar(@Parameter(description = "Representa o ID de um usuario", example = "1", required = true) Long usuarioId, 
			@RequestBody(description = "Representação de um usuario com dados atualizados")
			UsuarioInput usuarioInput);
	
	@Operation(summary = "Atualiza a senha do usuario por ID", description = "Atualização de senha de um usuario por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Senha do usuario atualizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Usuário não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public void atualizarSenha(@Parameter(description = "Representa o ID de um usuario", example = "1", required = true) Long usuarioId, 
			@RequestBody(description = "Representação de uma nova senha do usuario", required = true) 
			SenhaInput senhaInput);
	
	@Operation(summary = "Remove o usuario por ID", description = "Remoção de um usuario por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
					@ApiResponse(responseCode = "400", description = "Usuário não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public void remover(@Parameter(description = "Representa o ID de um usuario", example = "1", required = true) Long usuarioId);
}
