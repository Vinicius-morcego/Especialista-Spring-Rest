package com.algaworks.algafood.api.v2.openapi.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LinksModelV2OpenApi {

	private LinkModel linkModel;

	@Getter
	@Setter
	private class LinkModel{
		private String rel;
		private boolean templated;
	}
}
