package com.algaworks.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
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
