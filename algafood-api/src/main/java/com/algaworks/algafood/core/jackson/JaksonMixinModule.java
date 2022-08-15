package com.algaworks.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.mixin.CidadeMixin;
import com.algaworks.algafood.api.model.mixin.GrupoMixin;
import com.algaworks.algafood.api.model.mixin.RestauranteMixin;
import com.algaworks.algafood.api.model.mixin.UsuarioMixin;
import com.algaworks.algafood.domain.modelo.Cidade;
import com.algaworks.algafood.domain.modelo.Grupo;
import com.algaworks.algafood.domain.modelo.Restaurante;
import com.algaworks.algafood.domain.modelo.Usuario;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JaksonMixinModule extends SimpleModule{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JaksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Grupo.class, GrupoMixin.class);
		setMixInAnnotation(Usuario.class, UsuarioMixin.class);
	}

}
