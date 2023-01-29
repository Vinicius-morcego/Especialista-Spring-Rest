package com.algaworks.algafood.infrastructure.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService{
	@Value("${algafood.storage.local.diretorio-fotos}")
	private Path diretorioFotos;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {			
			throw new StorageException("Não foi possivel armazenar o arquivo.", e);
		}
	}
	
	@Override
	public void remover(String nomeArquivo) {
	
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {			
			throw new StorageException("Não foi possivel remover o arquivo.", e);
		}
		
	}
	
	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
					.inputStream(Files.newInputStream(arquivoPath))
					.build();
					
			return fotoRecuperada;
		} catch (Exception e) {			
			throw new StorageException("Não foi possivel recuperar o arquivo.", e);
		}
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return  storageProperties.getLocal().getDiretorioFotos().resolve(nomeArquivo);
	}

}
