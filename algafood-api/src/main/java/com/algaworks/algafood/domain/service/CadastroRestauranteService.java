package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.modelo.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_ENTIDADE_EM_USO = "Entidade de código %d em uso";

	

	@Autowired
	private RestauranteRepository restauranteRepository;
	
//	@Autowired 
//	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
			try {
				Long cozinhaId = restaurante.getCozinha().getId();	
				
				Cozinha cozinha = cadastroCozinhaService.busarOuFalhar(cozinhaId);
				
				restaurante.setCozinha(cozinha);
				
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
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}
	
	
	
	
}
