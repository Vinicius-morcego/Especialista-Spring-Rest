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
	
	public Produto buscarOuFalhar(Long produtoId) {
		return produtoRepository.findById(produtoId).orElseThrow(() ->
		new ProdutoNaoEncontradoException(String.format("Produto com o id %d não encontrado", produtoId)));
	}
	
}
