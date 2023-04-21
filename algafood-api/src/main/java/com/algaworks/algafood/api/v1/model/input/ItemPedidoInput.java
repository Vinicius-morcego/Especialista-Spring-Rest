package com.algaworks.algafood.api.v1.model.input;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
