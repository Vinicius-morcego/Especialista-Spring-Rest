package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.modelo.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, 
	RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{

	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> findTodosRestaurantesByNome(String nome);
	
//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	//List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	List<Restaurante> findTop2RestaurantesByNomeContaining(String nome);
	
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	
	Optional<Restaurante> findRestauranteById(Long id);
	
	int countByCozinhaId(Long cozinhaId);
	
	boolean existsResponsavel(Long restauranteId, Long usuarioId);
	
	//public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
}
