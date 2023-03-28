package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.RestauranteModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RestaurantesBasicoModelOpenApi {

	private RestaurantesEmbeddedModelOpenApi _embedded;
	private Links _links;
	@Data
	public class RestaurantesEmbeddedModelOpenApi{		
		List<RestauranteModel> restaurantes;
	}
}
