package com.algaworks.algafood.domain.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.modelo.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long>{
	
	
	Optional<FormaPagamento> findFormaPagamentoById(Long id);
	
	@Query("select max(dataAtualizacao) from FormaPagamento")
	OffsetDateTime getUltimaDataAtualizacao();
	
	@Query("select max(dataAtualizacao) from FormaPagamento where id: formaPagamentoId")
	OffsetDateTime getUltimaDataAtualizacaoById(Long formaPagamentoId);

}
