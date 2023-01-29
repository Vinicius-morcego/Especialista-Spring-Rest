package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;
import com.algaworks.algafood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.algaworks.algafood.domain.modelo.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;



@RestController
@RequestMapping(path = "/v1/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi{
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<UsuarioModel> listar(){
		List<Usuario> todosUsuarios = usuarioRepository.findAll(); 
		return usuarioModelAssembler.toCollectionModel(todosUsuarios);
	}
	
	@GetMapping(path = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		var usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		var usuarioModel = usuarioModelAssembler.toModel(usuario);
		
		return usuarioModel; 
	}	 
	 
	@PostMapping(path = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		var usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		return usuarioModelAssembler.toModel(cadastroUsuario.salvar(usuario));
	}
	
	@PutMapping(path = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		var usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
		usuarioModelAssembler.copyDomainToObject(usuarioInput, usuarioAtual);
		return usuarioModelAssembler.toModel(cadastroUsuario.salvar(usuarioAtual));
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@PathVariable Long usuarioId, @RequestBody SenhaInput senhaInput) {
		cadastroUsuario.atualizarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
		
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId) {
		cadastroUsuario.remover(usuarioId);
	}
	
	
	

}
