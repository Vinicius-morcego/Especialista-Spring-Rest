package com.algaworks.algafood.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.core.storage.StorageProperties.TipoStorage;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.infrastructure.storage.LocalFotoStorageService;
import com.algaworks.algafood.infrastructure.storage.S3FotoStorageService;

@Configuration
public class StorageConfig {
	
	@Autowired
	StorageProperties storageProperties = new StorageProperties();
//	
//	@Bean
//	public AmazonS3 amazonS3() {
//		var credentials = new BasicAWSCredentials(storageProperties.getS3().getIdChaveAcesso(), 
//				storageProperties.getS3().getChaveAcessoSecreta());
//		return AmazonS3ClientBuilder.standard()
//				.withCredentials(new AWSStaticCredentialsProvider(credentials))
//				.withRegion(storageProperties.getS3().getRegiao())
//				.build();
//	}

	@Bean
	public FotoStorageService fotoStorageService() {
		if(TipoStorage.S3.equals(storageProperties.getTipo()))
			return new S3FotoStorageService();
		else {
			return new LocalFotoStorageService();
		}	
	}
}
