package com.algaworks.algafood.domain.repository;

import java.util.Optional;

import com.algaworks.algafood.domain.modelo.Produto;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{

	Optional<Produto> findProdutoById(Long id);
}
