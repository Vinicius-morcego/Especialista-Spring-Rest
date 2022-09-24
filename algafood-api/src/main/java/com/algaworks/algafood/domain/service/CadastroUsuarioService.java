package com.algaworks.algafood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(String.format("Já existe um usuario com esse email : %s", usuario.getEmail()));
		}
		return usuarioRepository.save(usuario);
	}	
		
	@Transactional
	public void atualizarSenha(Long id, String senhaAtual, String novaSenha) {
		var usuario = buscarOuFalhar(id);
		if(validaSenhaAtual(senhaAtual, usuario)) {
			usuario.setSenha(novaSenha);
		}
		
	}
	
	public boolean validaSenhaAtual(String senhaAtual, Usuario usuario) {
		if(!senhaAtual.equals(usuario.getSenha())) {
			throw new NegocioException("Senha atual do usuário não confere, favor tente novamente!");
		}
		return true;
	}
	
	@Transactional
	public boolean desassociar(Long usuarioId, Long grupoId) {
		var usuario = buscarOuFalhar(usuarioId);
		var grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		return usuario.desassociar(grupo);
	}
	
	@Transactional
	public boolean associar(Long usuarioId, Long grupoId) {
		var usuario = buscarOuFalhar(usuarioId);
		var grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		return usuario.associar(grupo);
	}
	
	@Transactional
	public void remover(Long id) {
		Usuario usuario = buscarOuFalhar(id);
		usuarioRepository.delete(usuario);
	}
	
	public Usuario buscarOuFalhar(Long usuarioId) {		
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> 
		new UsuarioNaoEncontradoException(usuarioId));	
	}
}
