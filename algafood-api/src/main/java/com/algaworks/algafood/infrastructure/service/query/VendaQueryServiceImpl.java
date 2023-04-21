package com.algaworks.algafood.infrastructure.service.query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.modelo.Pedido;
import com.algaworks.algafood.domain.modelo.StatusPedido;
import com.algaworks.algafood.domain.modelo.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		var predicates = new ArrayList<Predicate>();
		
		var functionConvertTzDataCriacao = builder.function(
				"convert_tz", Date.class, root.get("dataCriacao"),
				builder.literal("+00:00"), builder.literal(timeOffset));
		
		var functionDateDataCriacao = builder.function("date", LocalDate.class, functionConvertTzDataCriacao);		
		
		if(filtro.getRestauranteId() != null)
			predicates.add(builder.equal(root.get("restaurante").get("id"), filtro.getRestauranteId()));
		if(filtro.getDataCriacaoInicio() != null)
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacaoInicio"), filtro.getDataCriacaoInicio()));
		if(filtro.getDataCriacaoFim() != null)
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacaoFim"), filtro.getDataCriacaoFim()));
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
			
		var selections = builder.construct(VendaDiaria.class, 
				functionDateDataCriacao, builder.count(root.get("id")),
						builder.sum(root.get("valorTotal")));
		
		query.select(selections);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
		
	}

}
