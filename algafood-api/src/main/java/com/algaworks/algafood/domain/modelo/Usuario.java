package com.algaworks.algafood.domain.modelo;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "nome_usuario")
	private String nome;
	
	@Column(name = "email_usuario")
	private String email;
	
	@Column(name = "senha_usuario")	
	private String senha;	
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;	
	
	@ManyToMany
	@JoinTable(name = "usuario_grupo",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	
	public boolean desassociar(Grupo grupo) {
		return getGrupos().remove(grupo);
	}
	
	public boolean associar(Grupo grupo) {
		return getGrupos().add(grupo);
	}
	
	public boolean verificaSeUsuarioEstaCadastrado(String email) {
		return email.equals(getEmail());
	}
	
	public boolean isNovo() {
		return getId() == null;
	}

}
