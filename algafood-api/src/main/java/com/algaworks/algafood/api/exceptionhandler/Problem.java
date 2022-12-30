package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	@ApiModelProperty(example = "https://algafood.com.br/erro-de-digitacao", position = 5)
	private String type;
	@ApiModelProperty(example = "Erro de digitação", position = 10)
	private String title;
	@ApiModelProperty(example = "Requisição inválida. Provavel erro de digitação.", position = 15)
	private String detail;
	@ApiModelProperty(example = "Requisição inválida. Provavel erro de digitação.", position = 20)
	private String userMessage;
	@ApiModelProperty(example = "2022-12-30T20:40:39.1998723Z", value = "Data no fomato ISO", position = 25)
	private OffsetDateTime timestamp;
	@ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)", position = 30)
	private List<Object> objects;
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object{
		@ApiModelProperty(example = "preco")
		private String name;
		@ApiModelProperty(example = "O preco é obrigatório")
		private String userMessage;
	}
	
	

}
