package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.ProdutoModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProdutosModelOpenApi {

	private ProdutosEmebeddedModelOpenApi _embedded;
	private Links _links;

	@Data
	public class ProdutosEmebeddedModelOpenApi{
		private List<ProdutoModel> produtos;
	}
}
