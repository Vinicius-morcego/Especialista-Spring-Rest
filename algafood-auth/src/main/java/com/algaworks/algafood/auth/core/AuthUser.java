package com.algaworks.algafood.auth.core;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.algafood.auth.domain.Usuario;

import lombok.Getter;

@Getter
public class AuthUser extends User{
	
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String fullName;
	public AuthUser(Usuario usuario) {
		super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());
		this.userId = usuario.getId();
		this.fullName = usuario.getNome();
	}


}
