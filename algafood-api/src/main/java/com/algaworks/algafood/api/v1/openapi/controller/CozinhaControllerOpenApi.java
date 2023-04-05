package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@Parameter(
				in = ParameterIn.QUERY,
				name = "page",
				description = "Número da página (0...N)",
				schema = @Schema(type = "integer", defaultValue = "0")
	)
	@Parameter(
			in = ParameterIn.QUERY,
			name = "size",
			description = "Quantidade de elementos por página",
			schema = @Schema(type = "integer", defaultValue = "10")
	)
	@Parameter(
			in = ParameterIn.QUERY,
			name = "sort",
			description = "Critérios de ordenação: propriedade(asc|desc)",
			schema = @Schema(type = "", defaultValue = ""),
			examples = {
					@ExampleObject("nome"),
					@ExampleObject("nome,asc"),
					@ExampleObject("nome,desc")
			}
	)
	PagedModel<CozinhaModel> listar(@Parameter(hidden = true) Pageable pageable);
	
	@Operation(summary = "Lista a cozinha por ID", description = "Lista uma cozinha por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID de cidade inválido",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	CozinhaModel buscar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);
	
	@Operation(summary = "Cadastro de cozinha", description = "Cadastra uma cozinha, "
			+ "necessita de um nome válido")	
	CozinhaModel adicionar(@RequestBody(description = "Representação de uma nova cozinha", required = true) CozinhaInput cozinhaInput);
	
	@Operation(summary = "Atualiza uma cozinha por ID", description = "Atualização de uma cozinha por ID, "
			+ "necessida de um ID válido")
	CozinhaModel atualizar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId,
			@RequestBody(description = "Representação de uma cozinha com dados atualizados", required = true) CozinhaInput cozinhaInput);
	
	@Operation(summary = "Remove uma cozinha por ID", description = "Remoção de uma cozinha por ID, "
			+ "necessida de um ID válido")
	void remover(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);
}
