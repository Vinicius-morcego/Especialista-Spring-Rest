package com.algaworks.algafood.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v2.model.CozinhaModelV2;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CozinhasModelV2OpenApi {

	private CozinhasEmbeddedModelV2OpenApi _embedded;
	private Links _links;
	private PageModelV2OpenApi page;

	@Data
	public class CozinhasEmbeddedModelV2OpenApi{
		List<CozinhaModelV2> cozinhas;
		
	}
}
