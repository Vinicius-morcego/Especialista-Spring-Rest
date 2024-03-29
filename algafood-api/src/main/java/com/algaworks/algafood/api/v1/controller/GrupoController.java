package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

import com.algaworks.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/grupos")
public class GrupoController implements GrupoControllerOpenApi {
	
	public static String MSG_ENTIDADE_EM_USO = "Grupo de código %d está em uso e não pode ser excluído";

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoModelAssembler grupoAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) 
	public CollectionModel<GrupoModel> listar(){
		List<Grupo> todosGrupos = grupoRepository.findAll();
		return grupoAssembler.toCollectionModel(todosGrupos);		
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping(path = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		return grupoAssembler.toModel(grupo);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PostMapping(path = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel salvar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		
		return grupoAssembler.toModel(grupoRepository.save(grupo));
		
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping(path = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		grupoAssembler.copyToDomainObject(grupoInput, grupoAtual);
		
		return grupoAssembler.toModel(cadastroGrupoService.salvar(grupoAtual));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{grupoId}")
	public void excluir(@PathVariable Long grupoId) {
		try {
			cadastroGrupoService.remover(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, grupoId));
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(e.getMessage(), grupoId);
		}
		
		
	}
	
}
