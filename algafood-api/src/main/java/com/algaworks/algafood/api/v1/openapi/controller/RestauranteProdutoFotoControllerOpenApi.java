package com.algaworks.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteProdutoFotoControllerOpenApi {

	public FotoProdutoModel atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput,			
			MultipartFile arquivo) throws IOException;
	
	public FotoProdutoModel listar(Long produtoId, Long restauranteId);
	
	public void removerFotoProduto(Long restauranteId, Long produtoId);
		
	public ResponseEntity<InputStreamResource> servirFoto(Long produtoId, 
			Long restauranteId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
}
