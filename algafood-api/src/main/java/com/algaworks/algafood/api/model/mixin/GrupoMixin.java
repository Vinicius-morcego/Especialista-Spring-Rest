package com.algaworks.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.modelo.Permissao;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class GrupoMixin{

	@JsonIgnore
	private List<Permissao> permissoes = new ArrayList<>();
}
