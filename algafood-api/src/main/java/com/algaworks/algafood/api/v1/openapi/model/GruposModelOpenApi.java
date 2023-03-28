package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.GrupoModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GruposModelOpenApi {

	private GrupoEmbeddedModelOpenApi _embedded;
	private Links _links;
	@Data
	public class GrupoEmbeddedModelOpenApi{
		private List<GrupoModel> grupos;
	}
}
