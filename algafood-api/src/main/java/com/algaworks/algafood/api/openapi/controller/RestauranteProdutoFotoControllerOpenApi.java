package com.algaworks.algafood.api.openapi.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

	@ApiOperation("Atualiza a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante ou do produto inválido(s)", response = Problem.class),
		@ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
	})
	public FotoProdutoModel atualizarFoto(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = "ID do produto", example = "1", required = true) 
			Long produtoId,
			@ApiParam(value = "corpo", example = "Representação de um produto de um restaurante", required = true)
			FotoProdutoInput fotoProdutoInput,
			@ApiParam(value = "Arquivo da foto do produto (maximo 500KB, apenas JPEG, PNG)", required = true)
			MultipartFile arquivo) throws IOException;
	
	@ApiOperation("Busca a foto do produto de um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do produto ou do restaurante inválidos", response = Problem.class),
		@ApiResponse(code = 400, message = "Foto do produto não encontrada", response = Problem.class)
	})
	public FotoProdutoModel listar(
			@ApiParam(value = "ID do produto", example = "1", required = true) 
			Long produtoId, 
			@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId);
	
	@ApiOperation("Exclui a foto do produto do restaurante")
	public void removerFotoProduto(
			@ApiParam(value = "ID do restaurante", example = "1", required = true) 
			Long restauranteId, 
			@ApiParam(value = "ID do produto", example = "1", required = true) 
			Long produtoId);
	
	@ApiOperation(value = "Busca a foto do produto de um restaurante por ID", hidden = true)	
	public ResponseEntity<InputStreamResource> servirFoto(Long produtoId, 
			Long restauranteId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
}
