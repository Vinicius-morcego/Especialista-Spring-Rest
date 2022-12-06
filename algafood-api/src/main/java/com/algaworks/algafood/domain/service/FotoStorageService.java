package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.Builder;
import lombok.Getter;

@Service
public interface FotoStorageService {

	void armazenar(NovaFoto novaFoto);
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString()+"_"+nomeOriginal;
	}
	
	@Getter
	@Builder
	class NovaFoto{
		private String nomeArquivo;
		private InputStream inputStream;
	}
}
