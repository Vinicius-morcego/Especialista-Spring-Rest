package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FormasPagamentoModelOpenApi {

	private FormaPagamentoEmbeddedModelOpenApi _embedded;
	private Links _links;
	@Data
	public class FormaPagamentoEmbeddedModelOpenApi{
		List<FormaPagamentoModel> formasPagamento;
	}
}
