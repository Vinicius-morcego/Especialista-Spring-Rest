package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface ProdutoControllerOpenApi {

	public static String ID_DO_PRODUTO = "ID do produto";
	public static String ID_DO_RESTAURANTE = "ID do restaurante";
	public static String MSG_NOT_FOUND = "Produto do restaurante não encontrado";
	public static String MSG_BAD_REQUEST = "ID do restaurante inválido";
	
	@Operation(summary = "Lista os produtos por ID do restaurante", description = "Lista os produtos por ID de um restaurante, "
			+ "necessita de um ID válido")
	public CollectionModel<ProdutoModel> listar(Long restauranteId, Boolean incluirIntativos);
	
	@Operation(summary = "Lista o produto por ID do restaurante e por ID do produto", description = "Lista o produto por ID de um restaurante "
			+ " e por ID de um produto, necessita de um ID válido")
	@GetMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel buscar(Long restauranteId, Long produtoId);	
	
	@Operation(summary = "Cadastro do produto", description = "Cadastro de um produto, "
			+ "necessita de uma descrição válida")
	public ProdutoModel salvar(Long restauranteId, ProdutoInput produtoInput);
	
	@Operation(summary = "Atualiza o produto por ID", description = "Atualização de um produto por ID, "
			+ "necessita de um ID válido")
	public ProdutoModel atualizar(Long restauranteId, Long produtoId, ProdutoInput produtoInput);
}
