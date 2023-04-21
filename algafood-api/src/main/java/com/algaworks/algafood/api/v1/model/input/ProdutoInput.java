package com.algaworks.algafood.api.v1.model.input;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ProdutoInput {

	@Schema(example = "Picanha na chapa")
	@NotBlank
	private String nome;
	
	@Schema(example = "Picanha cozida na chapa de pedra")
	@NotBlank
	private String descricao;
	
	@Schema(example = "R$12,00")
	@NotNull
	private BigDecimal preco;	
	
	@Schema(example = "Status ativo")
	@NotNull
	private Boolean ativo;
}
