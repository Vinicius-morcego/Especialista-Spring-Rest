package com.algaworks.algafood.api.v2.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Links")
@Getter
@Setter
public class LinksModelV2OpenApi {

	private LinkModel linkModel;
	
	@ApiModel("Link")
	@Getter
	@Setter
	private class LinkModel{
		private String rel;
		private boolean templated;
	}
}
