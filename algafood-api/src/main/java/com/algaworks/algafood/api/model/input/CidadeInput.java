package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

	//private Long id;
	@ApiModelProperty(value = "Nome da cidade", example = "Campina Verde")
	private String nome;
	private EstadoIdInput estado;
}
