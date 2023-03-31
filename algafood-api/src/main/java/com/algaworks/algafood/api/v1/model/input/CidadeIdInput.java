package com.algaworks.algafood.api.v1.model.input;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CidadeIdInput {	
	@Schema(example = "1")
	private Long id;
}
