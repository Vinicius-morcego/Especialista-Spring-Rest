package com.algaworks.algafood.api.v2.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Pageable")
@Getter
@Setter
public class PageableModelV2OpenApi {

	@ApiModelProperty(value = "0", example = "Número da página (começa em 0)")
	private int page;
	
	@ApiModelProperty(value = "10", example = "Quantidade de elementos por página")
	private int size;
	
	@ApiModelProperty(value = "nome,asc", example = "Nome da propriedade para ordenação")
	private List<String> sort;
}
