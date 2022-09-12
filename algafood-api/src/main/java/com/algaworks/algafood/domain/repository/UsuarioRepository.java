package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafood.domain.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
