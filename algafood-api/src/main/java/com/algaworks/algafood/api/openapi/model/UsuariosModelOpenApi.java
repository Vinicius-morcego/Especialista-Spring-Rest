package com.algaworks.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("UsuarioModel")
@Getter
@Setter
public class UsuariosModelOpenApi {
	
	private UsuariosEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("UsuariosEmbeddedModel")
	@Data
	public class UsuariosEmbeddedModelOpenApi{
		private List<UsuarioModel> usuarios;
	}

}
