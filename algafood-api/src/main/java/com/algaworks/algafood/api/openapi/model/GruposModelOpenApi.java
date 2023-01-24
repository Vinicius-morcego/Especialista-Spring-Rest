package com.algaworks.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.model.GrupoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("GrupoModel")
@Getter
@Setter
public class GruposModelOpenApi {

	private GrupoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("GrupoEmbeddedModel")
	@Data
	public class GrupoEmbeddedModelOpenApi{
		private List<GrupoModel> grupos;
	}
}
