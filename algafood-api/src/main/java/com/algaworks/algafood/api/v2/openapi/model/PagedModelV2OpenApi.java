package com.algaworks.algafood.api.v2.openapi.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PagedModelV2OpenApi<T> {

	private List<T> content;
	private Long size;
	private Long totalElements;
	private Long totalPages;
	private Long number;
}
