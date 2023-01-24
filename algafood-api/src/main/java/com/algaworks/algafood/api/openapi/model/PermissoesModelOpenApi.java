package com.algaworks.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.model.PermissaoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PermissaoModel")
@Getter
@Setter
public class PermissoesModelOpenApi {

	private PermissaoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("PermissaoEmbeddedModel")
	@Data
	public class PermissaoEmbeddedModelOpenApi{		
		private List<PermissaoModel> permissões;
	}
}
