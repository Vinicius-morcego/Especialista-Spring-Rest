package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {
	
	public static String MSG_GRUPO_NAO_ENCONTRADO = "O Grupo com o id %d não foi encontrado!";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		
		return grupoRepository.save(grupo);	
		
	}
	
	@Transactional
	public void remover(Long id) {
		Grupo grupo = buscarOuFalhar(id);
		grupoRepository.delete(grupo);
	}
	
	@Transactional
	public void desassociar(Long grupoId, Long permissaoId) {
		var grupo = buscarOuFalhar(grupoId);
		var permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);		
		grupo.desassociar(permissao);
	}
	
	@Transactional
	public void associar(Long grupoId, Long permissaoId) {
		var grupo = buscarOuFalhar(grupoId);
		var permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);		
		grupo.associar(permissao);
	}
	
	public Grupo buscarOuFalhar(Long grupoId) {		
		return grupoRepository.findById(grupoId).orElseThrow(() -> 
			new GrupoNaoEncontradoException(MSG_GRUPO_NAO_ENCONTRADO, grupoId));
	}
	
}
