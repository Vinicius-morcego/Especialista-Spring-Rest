package com.algaworks.algafood.api.v1.openapi.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LinksModelOpenApi {

	private LinkModel linkModel;
	@Getter
	@Setter
	private class LinkModel{
		private String rel;
		private boolean templated;
	}
}
