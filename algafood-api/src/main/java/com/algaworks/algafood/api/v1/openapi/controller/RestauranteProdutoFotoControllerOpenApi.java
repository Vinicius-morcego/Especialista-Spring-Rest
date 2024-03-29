package com.algaworks.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteProdutoFotoControllerOpenApi {

	@Operation(summary = "Atualiza a foto do produto por filtro", description = "Atualização da foto de um produto pelo filtro informado, "
			+ "necessita de um filtro válido", responses = {
					@ApiResponse(responseCode = "200", description = "Foto do produto atualizada com sucesso"),
					@ApiResponse(responseCode = "400", description = "Foto do produto não encontrada",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	public FotoProdutoModel atualizarFoto(
			@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "Representa o ID de um produto", example = "1", required = true) Long produtoId, 
			@RequestBody(description = "Representação de uma nova foto de um produto", required = true)
			FotoProdutoInput fotoProdutoInput) throws IOException;
	
	@Operation(summary = "Busca a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", description = "Foto encontrada com sucesso", 
					content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = FotoProdutoModel.class)),
						@Content(mediaType = "application/jpeg", schema = @Schema(type = "string", format = "binary")),
						@Content(mediaType = "application/png", schema = @Schema(type = "string", format = "binary"))
					}),
			@ApiResponse(responseCode = "400", description = "Foto do produto não encontrada")
	})
	public FotoProdutoModel listar(Long produtoId, Long restauranteId);
	
	@Operation(summary = "Remove a foto do produto por ID", description = "Remoção de uma foto de um produto por ID, "
			+ "necessita de um ID válido")
	public void removerFotoProduto(@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "Representa o ID de um produto", example = "1", required = true) Long produtoId);
		
	@Operation(hidden = true)
	public ResponseEntity<InputStreamResource> servirFoto(@Parameter(description = "Representa o ID de um produto", example = "1", required = true) Long produtoId, 
			@Parameter(description = "Representa o ID de um restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "Representa o tipo de retorno aceito pela API", example = "application/json", required = true) String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
}
