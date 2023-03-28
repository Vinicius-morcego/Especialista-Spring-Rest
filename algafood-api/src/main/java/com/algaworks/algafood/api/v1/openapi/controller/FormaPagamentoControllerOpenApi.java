package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;


public interface FormaPagamentoControllerOpenApi {

	ResponseEntity<CollectionModel<FormaPagamentoModel>> consultar(ServletWebRequest request);		
	ResponseEntity<FormaPagamentoModel> buscar(Long formaPagamentoId, ServletWebRequest request);	
	FormaPagamentoModel salvar(FormaPagamentoInput formaPagamentoInput);
	void excluir(Long formaPagamentoId);
}
