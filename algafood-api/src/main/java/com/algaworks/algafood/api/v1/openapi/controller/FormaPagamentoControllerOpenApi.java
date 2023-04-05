package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

	@Operation(summary = "Lista as formas de pagamento")
	ResponseEntity<CollectionModel<FormaPagamentoModel>> consultar(ServletWebRequest request);
	
	@Operation(summary = "Lista a forma de pagamento por ID", description = "Lista uma forma de pagamento por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID de forma de pagamento inválido",
							content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	ResponseEntity<FormaPagamentoModel> buscar(
			@Parameter(description = "Representa o ID da forma de pagamento", example = "1", required = true) 
			Long formaPagamentoId, ServletWebRequest request);
	
	@Operation(summary = "Cadastro de forma de pagamento", description = "Cadastro de uma forma de pagamento, "
			+ "necessita de uma descrição válida")
	FormaPagamentoModel salvar(@RequestBody(description = "Representação de uma nova forma de pagamento", required = true) 
	FormaPagamentoInput formaPagamentoInput);
	
	@Operation(summary = "Remove a forma de pagamento por ID", description = "Remoção de uma forma de pagamento por ID, "
			+ "necessita de um ID válido", responses = {
					@ApiResponse(responseCode = "400", description = "ID de forma de pagamento inválido",
							content = @Content(schema = @Schema(ref = "Problem"))),
					@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrado",
							content = @Content(schema = @Schema(ref = "Problem")))
			})
	void excluir(@Parameter(description = "Representa o ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);
}
