package com.algaworks.algafood.infrastructure.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;

//@Service
public class S3FotoStorageService implements FotoStorageService{
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired 
	private StorageProperties storageProperties;

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {		
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
		
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);
		
		return FotoRecuperada.builder()
				.url(url.toString())
				.build();
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
		
//		try {
//			String arquivoPath = getCaminhoArquivo(nomeArquivo);
//			
//			var delectObjectRequest = new DeleteObjectRequest(
//					storageProperties.getS3().getBucket(), arquivoPath);
//			
//			amazonS3.deleteObject(delectObjectRequest);
//			
//		} catch (Exception e) {
//			throw new StorageException("Não foi possivel remover o arquivo", e);
//			
//		}
		
	}
	private String getCaminhoArquivo(String nomeArquivo) {
		
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}


}
