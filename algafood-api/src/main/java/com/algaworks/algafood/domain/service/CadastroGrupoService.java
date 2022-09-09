package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {
	
	public static final String MSG_GRUPO_NAO_ENCONTRADO = "O Grupo com o id %d não foi encontrado!";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public Grupo salvar(Grupo grupo) {
		
		return grupoRepository.save(grupo);	
		
	}
	
	public Grupo atualizar(Grupo grupo, Long id) {
		grupo = buscarOuFalhar(id);
		
		return grupoRepository.save(grupo);
	}
	
	public Grupo buscarOuFalhar(Long id) {
		
		return grupoRepository.findById(id).orElseThrow(() -> 
			new GrupoNaoEncontradoException(MSG_GRUPO_NAO_ENCONTRADO, id));
	}
	
	public void remover(Long id) {
		Grupo grupo = buscarOuFalhar(id);
		grupoRepository.delete(grupo);
	}
}
