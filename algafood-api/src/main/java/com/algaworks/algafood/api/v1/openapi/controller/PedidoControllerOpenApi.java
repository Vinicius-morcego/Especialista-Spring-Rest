package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

public interface PedidoControllerOpenApi {
	
	
	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filter,
			@PageableDefault(size = 2) Pageable pageable);
	
	PedidoModel buscar(String codigoPedido);
	PedidoModel adicionar(PedidoInput pedidoInput);

}
