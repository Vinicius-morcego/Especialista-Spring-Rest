package com.algaworks.algafood.infrastructure.repository;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.modelo.Pedido;
import com.algaworks.algafood.domain.modelo.StatusPedido;
import com.algaworks.algafood.domain.modelo.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		var predicates = new ArrayList<Predicate>();
		var functionDateDataCriacao = builder.function("date", LocalDate.class, root.get("dataCriacao"));
		
		if(filtro.getRestauranteId() != null)
			predicates.add(builder.in(root.get("restauranteId")));
		if(filtro.getDataCriacaoInicio() != null)
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacaoInicio"), filtro.getDataCriacaoInicio()));
		if(filtro.getDataCriacaoFim() != null)
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacaoFim"), filtro.getDataCriacaoInicio()));
		
		predicates.add(builder.in(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE)));
			
		var selections = builder.construct(VendaDiaria.class, 
				functionDateDataCriacao, builder.count(root.get("id")),
						builder.sum(root.get("valorTotal")));
		
		query.select(selections);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
		
	}

}
