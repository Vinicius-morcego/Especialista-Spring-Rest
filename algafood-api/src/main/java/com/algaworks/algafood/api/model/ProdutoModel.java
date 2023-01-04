package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ProdutoModel {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;
	
	@ApiModelProperty(example = "Carne de porco")
	@NotBlank
	private String nome;	
		
	@ApiModelProperty(example = "Carne de porco ao molho barbecue")
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(example = "20.00")
	@NotNull
	private BigDecimal preco;
	
	@NotNull
	private Boolean ativo;
	
	
}
