package com.algaworks.algafood.api.v2.openapi.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PageableModelV2OpenApi {

	private int page;
	private int size;
	private List<String> sort;
}
