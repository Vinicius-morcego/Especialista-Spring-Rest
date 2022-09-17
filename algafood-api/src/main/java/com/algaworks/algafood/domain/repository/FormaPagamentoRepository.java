package com.algaworks.algafood.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.modelo.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long>{
	
	
	Optional<FormaPagamento> findFormaPagamentoById(Long id);

}
