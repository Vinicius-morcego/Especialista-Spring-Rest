package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.openapi.model.FormasPagamentoModelOpenApi;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation(value = "Lista as formas de pagamento")
	@io.swagger.annotations.ApiResponses(value = {
	@io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = FormasPagamentoModelOpenApi.class)
	})	
		ResponseEntity<CollectionModel<FormaPagamentoModel>> consultar(
			@ApiParam(example = "corpo", value = "Representação de formas de pagamento")
			ServletWebRequest request);
	
	@ApiOperation("Busca uma forma de pagamento pelo ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})	
		ResponseEntity<FormaPagamentoModel> buscar(
			@ApiParam(example = "1", required = true) 
			Long formaPagamentoId, 
			@ApiParam
			ServletWebRequest request);
	
	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de pagamento cadastrada")
	})
		FormaPagamentoModel salvar(
			@ApiParam(example = "corpo", value = "Representação de uma forma de pagamento", required = true)
			FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Remove forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento excluída")
		
	})
		void excluir(@ApiParam(
			value = "ID de uma forma de pagamento", example = "1", required = true) 
			Long formaPagamentoId);
}
