package com.algaworks.algafood.infrastructure.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

//@Service
public class S3FotoStorageService implements FotoStorageService{
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired 
	private StorageProperties storageProperties;

	@Override
	public InputStream recuperar(String nomeArquivo) {		
		return null;
	}

	@Override
	public void armazenar(NovaFoto novaFoto) {
//		try {
//			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
//			
//			var objectMetaData = new ObjectMetadata();
//			objectMetaData.setContentType(novaFoto.getContentType());
//			
//			var putObjectRequest = new PutObjectRequest(storageProperties.getS3().getBucket(), 
//					caminhoArquivo, 
//					novaFoto.getInputStream(), 
//					objectMetaData)
//					.withCannedAcl(CannedAccessControlList.PublicRead);
//			amazonS3.putObject(putObjectRequest);
//			
//		} catch (Exception e) {
//			throw new StorageException("Não foi possivel enviar arquivo para Amazon S3", e);
//		}
		
		
	}

	@Override
	public void remover(String nomeArquivo) {
		
		try {
			String arquivoPath = getCaminhoArquivo(nomeArquivo);
			
			var delectObjectRequest = new DeleteObjectRequest(
					storageProperties.getS3().getBucket(), arquivoPath);
			
			amazonS3.deleteObject(delectObjectRequest);
			
		} catch (Exception e) {
			throw new StorageException("Não foi possivel remover o arquivo", e);
			
		}
		
	}
	private String getCaminhoArquivo(String nomeArquivo) {
		
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}


}
