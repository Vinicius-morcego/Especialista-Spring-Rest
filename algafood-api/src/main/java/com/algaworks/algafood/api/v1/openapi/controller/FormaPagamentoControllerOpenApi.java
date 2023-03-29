package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

	@Operation(summary = "Lista as formas de pagamento")
	ResponseEntity<CollectionModel<FormaPagamentoModel>> consultar(ServletWebRequest request);
	
	@Operation(summary = "Lista a forma de pagamento por ID", description = "Lista uma forma de pagamento por ID, "
			+ "necessita de um ID válido")
	ResponseEntity<FormaPagamentoModel> buscar(Long formaPagamentoId, ServletWebRequest request);
	
	@Operation(summary = "Cadastro de forma de pagamento", description = "Cadastro de uma forma de pagamento, "
			+ "necessita de uma descrição válida")
	FormaPagamentoModel salvar(FormaPagamentoInput formaPagamentoInput);
	
	@Operation(summary = "Remove a forma de pagamento por ID", description = "Remoção de uma forma de pagamento por ID, "
			+ "necessita de um ID válido")
	void excluir(Long formaPagamentoId);
}
