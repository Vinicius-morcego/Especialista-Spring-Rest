package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	public static String MSG_BAD_REQUEST = "ID do restaurante ou da forma de pagamento inválido(s)";
	public static String MSG_NOT_FOUND = "Restaurante ou forma de pagamento não encontrado(s)";

	@Operation(summary = "Lista as formas de pagamento do restaurante pelo ID", description = "Lista as formas de pagamento de um restaurnate por ID, "
			+ "necessita de um ID válido")
	public CollectionModel<FormaPagamentoModel> listar(Long restauranteId);
	
	@Operation(summary = "Desassocia a forma de pagamento do restaurante por ID", description = "Desassocia uma forma de pagamento de um restaurante por ID, "
			+ "necessita de um ID válido")
	public ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);	
	
	@Operation(summary = "Associa a forma de pagamento do restaurante por ID", description = "Associa uma forma de pagamento de um restaurante por ID, "
			+ "necessita de um ID válido")
	public ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);
}
