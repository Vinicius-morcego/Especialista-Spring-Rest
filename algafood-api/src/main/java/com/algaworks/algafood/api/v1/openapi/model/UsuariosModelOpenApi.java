package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuariosModelOpenApi {
	
	private UsuariosEmbeddedModelOpenApi _embedded;
	private Links _links;

	@Data
	public class UsuariosEmbeddedModelOpenApi{
		private List<UsuarioModel> usuarios;
	}

}
