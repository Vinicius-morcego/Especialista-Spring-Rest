package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;


public interface ProdutoControllerOpenApi {

	public static String ID_DO_PRODUTO = "ID do produto";
	public static String ID_DO_RESTAURANTE = "ID do restaurante";
	public static String MSG_NOT_FOUND = "Produto do restaurante não encontrado";
	public static String MSG_BAD_REQUEST = "ID do restaurante inválido";
	
	public CollectionModel<ProdutoModel> listar(Long restauranteId, Boolean incluirIntativos);
	
	@GetMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel buscar(Long restauranteId, Long produtoId);	
	public ProdutoModel salvar(Long restauranteId, ProdutoInput produtoInput);
	
	public ProdutoModel atualizar(Long restauranteId, Long produtoId, ProdutoInput produtoInput);
}
