package com.algaworks.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.modelo.Cidade;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long>{

	
}
