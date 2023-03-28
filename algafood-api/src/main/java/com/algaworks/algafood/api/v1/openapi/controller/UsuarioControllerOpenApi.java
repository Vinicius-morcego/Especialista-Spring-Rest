package com.algaworks.algafood.api.v1.openapi.controller;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;


public interface UsuarioControllerOpenApi {

	public CollectionModel<UsuarioModel> listar();
	public UsuarioModel buscar(@PathVariable Long usuarioId);	 
	public UsuarioModel salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput);	
	public UsuarioModel atualizar(Long usuarioId, UsuarioInput usuarioInput);	
	public void atualizarSenha(Long usuarioId, SenhaInput senhaInput);	
	public void remover(Long usuarioId);
}
