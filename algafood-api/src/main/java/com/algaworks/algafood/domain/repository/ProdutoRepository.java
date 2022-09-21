package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.modelo.Restaurante;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{

	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param("restaurante") Long restauranteId,
			@Param("produto") Long produtoId);
	
	List<Produto> findByRestaurante(Restaurante restaurante);
}