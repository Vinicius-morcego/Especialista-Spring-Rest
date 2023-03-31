package com.algaworks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ItemPedidoInput {
	
	@Schema(example = "1")
	@Valid
	@NotNull
	private Long produtoId;
	
	@Schema(example = "10")
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	@Schema(example = "Comida fina com codimentos especiais")
	private String observacao;
}
