package com.algaworks.algafood.api.v1.openapi.controller;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuarios")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuarios")
	public CollectionModel<UsuarioModel> listar();
	
	@Operation(summary = "Lista o usuario por ID", description = "Lista um usuario por ID, "
			+ "necessita de um ID válido")
	public UsuarioModel buscar(@PathVariable Long usuarioId);	 
	
	@Operation(summary = "Cadastro do usuario", description = "Cadastro de um usuario, "
			+ "necessita de um login e uma senha válidos")
	public UsuarioModel salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput);	
	
	@Operation(summary = "Atualiza o usuario por ID", description = "Atualização de um usuario por ID, "
			+ "necessita de um ID válido")
	public UsuarioModel atualizar(Long usuarioId, UsuarioInput usuarioInput);
	
	@Operation(summary = "Atualiza a senha do usuario por ID", description = "Atualização de senha de um usuario por ID, "
			+ "necessita de um ID válido")
	public void atualizarSenha(Long usuarioId, SenhaInput senhaInput);
	
	@Operation(summary = "Remove o usuario por ID", description = "Remoção de um usuario por ID, "
			+ "necessita de um ID válido")
	public void remover(Long usuarioId);
}
