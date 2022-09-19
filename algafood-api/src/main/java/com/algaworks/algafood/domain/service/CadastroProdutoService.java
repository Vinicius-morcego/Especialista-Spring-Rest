package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {	 

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Produto salvar(Produto produto) {	
		
		return produtoRepository.save(produto);
	}	
	
	@Transactional
	public void ativar(Long produtoId, Long restauranteId) {
		var produtoAtual = buscarOuFalhar(produtoId, restauranteId);
		produtoAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long produtoId, Long restauranteId) {
		var produtoAtual = buscarOuFalhar(produtoId, restauranteId);
		produtoAtual.inativar();
	}
	
	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {	
		return produtoRepository.findById(restauranteId, produtoId).orElseThrow(() ->
		new ProdutoNaoEncontradoException(
			String.format("Produto com o id %d não encontrado para o restaurante %d", restauranteId, produtoId)));
	}
	
}
