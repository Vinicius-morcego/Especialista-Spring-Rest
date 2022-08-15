package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.modelo.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CidadeMixin {	
	@JsonIgnoreProperties(allowGetters = true, value = {"nome"})
	private Estado estado;
}
