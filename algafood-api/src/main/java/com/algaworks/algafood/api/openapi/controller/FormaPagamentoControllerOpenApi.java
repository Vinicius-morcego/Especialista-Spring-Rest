package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation("Lista formas de pagamento")
	@ApiResponses({
		@ApiResponse(code = 405, message = "Recurso não implementado", response = Problem.class)
	})	
	public ResponseEntity<List<FormaPagamentoModel>> consultar(
			@ApiParam(example = "corpo", value = "Representação de formas de pagamento")
			ServletWebRequest request);
	
	@ApiOperation("Busca uma forma de pagamento pelo ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})	
	public ResponseEntity<FormaPagamentoModel> buscar(
			@ApiParam(example = "1") 
			Long formaPagamentoId, 
			@ApiParam
			ServletWebRequest request);
	
	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de pagamento cadastrada")
	})
	public FormaPagamentoModel salvar(
			@ApiParam(example = "corpo", value = "Representação de uma forma de pagamento")
			FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Remove forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento excluída")
		
	})
	public void excluir(@PathVariable Long formaPagamentoId);
}
