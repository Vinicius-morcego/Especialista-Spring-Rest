package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Cidade;
import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.modelo.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_ENTIDADE_EM_USO = "Entidade de código %d em uso";	

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired 
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
			try {
				Long cozinhaId = restaurante.getCozinha().getId();	
				Long cidadeId = restaurante.getEndereco().getCidade().getId();
				
				Cozinha cozinha = cadastroCozinhaService.busarOuFalhar(cozinhaId);
				
				Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);
				
				restaurante.setCozinha(cozinha);
				restaurante.getEndereco().setCidade(cidade);
				
				return restauranteRepository.save(restaurante);
			} catch (CozinhaNaoEncontradaException e) {
				throw new NegocioException(e.getMessage(), e);
			}
			
		
		
	}
	
	@Transactional
	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(restauranteId);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, restauranteId));
		}
	}
	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		
		restauranteAtual.ativar();
		
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		
		restauranteAtual.inativar();
	}
	
	@Transactional
	public void ativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::inativar);
	}
	
	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		
		restauranteAtual.abertura();
		
	}
	
	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
		
		restauranteAtual.fechamento();
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> 
		new RestauranteNaoEncontradoException(restauranteId));
	}
	
	@Transactional
	public void desassociarFormasDePagamento(Long restauranteId, Long formaPagamentoId) {
		var restaurante = buscarOuFalhar(restauranteId);
		var formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		restaurante.removerFormasPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormasPagamento(Long restauranteId, Long formaPagamentoId) {
		var restaurante = buscarOuFalhar(restauranteId);
		var formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		restaurante.associarFormasPagamento(formaPagamento);
	}	
	
	@Transactional
	public void desassociar(Long restauranteId, Long usuarioId) {
		var restaurante = buscarOuFalhar(restauranteId);
		var usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		restaurante.desassociar(usuario);
	}
	
	@Transactional
	public void associar(Long restauranteId, Long usuarioId) {
		var restaurante = buscarOuFalhar(restauranteId);
		var usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		restaurante.associar(usuario);
	}
	
}
