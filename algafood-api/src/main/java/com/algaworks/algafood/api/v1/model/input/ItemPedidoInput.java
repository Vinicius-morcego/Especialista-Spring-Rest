package com.algaworks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ItemPedidoInput {

	@ApiModelProperty(example = "1")
	@Valid
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(example = "5")
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	@ApiModelProperty(example = "Costela servida ao ponto com molho madeira")
	private String observacao;
}
