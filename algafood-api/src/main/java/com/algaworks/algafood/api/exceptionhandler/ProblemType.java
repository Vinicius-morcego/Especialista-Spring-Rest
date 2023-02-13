package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ERRO_DE_DIGITACAO("/erro-de-digitacao", "Erro de digitação"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de Sistema"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),	
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),	
	ERRO_DE_NEGOCIO("/erro-de-negocio", "Violação de regra de negócio"),
	PROPRIEDADE_INEXISTENTE("/propriedade-inexistente", "Propriedade inexistente"),
	PROPRIEDADE_IGNORADA("/propriedade-ignorada", "Propriedade ignorada"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parametro inválido"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ACESSO_NEGADO("/acesso-negado", "Acesso negado");
	
	private String titulo;
	private String uri;
	
	ProblemType(String path, String titulo) {
		this.titulo = titulo;
		this.uri = "https://algafood.com.br" + path; 
	}
}
