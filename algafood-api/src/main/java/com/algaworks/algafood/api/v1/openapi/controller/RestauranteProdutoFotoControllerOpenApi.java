package com.algaworks.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteProdutoFotoControllerOpenApi {

	@Operation(summary = "Atualiza a foto do produto por filtro", description = "Atualização da foto de um produto pelo filtro informado, "
			+ "necessita de um filtro válido")
	public FotoProdutoModel atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput,			
			MultipartFile arquivo) throws IOException;
	
	@Operation(summary = "Busca a foto do produto pelo ID", description = "Busca a foto de um produto por ID, "
			+ "necessita de um ID válido")
	public FotoProdutoModel listar(Long produtoId, Long restauranteId);
	
	@Operation(summary = "Remove a foto do produto por ID", description = "Remoção de uma foto de um produto por ID, "
			+ "necessita de um ID válido")
	public void removerFotoProduto(Long restauranteId, Long produtoId);
		
	@Operation(summary = "Busca a foto de um produto por ID", description = "Busca a foto de um produto por ID "
			+ "e retorna no formato especificado, em PDF por exemplo, necessita de um ID válido")
	public ResponseEntity<InputStreamResource> servirFoto(Long produtoId, 
			Long restauranteId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
}
