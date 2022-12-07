package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.FotoProdutoNaoEncontradaException;
import com.algaworks.algafood.domain.modelo.FotoProduto;
import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	private static String MSG_FOTO_NAO_ENCONTRADA = "Foto do produto de id %d não encontrada para o restaurante de id %d";
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream inputStream) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		String nomeArquivoExistente = null;
		if(fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());			
		}
		foto.setNomeArquivo(nomeNovoArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.inputStream(inputStream)
				.build();
		
		fotoStorage.substituir(nomeArquivoExistente, novaFoto);		
		return foto;
	}
	
	@Transactional
	public FotoProduto buscarOuFalhar(Long produtoId, Long restauranteId) {
		return produtoRepository.findFotoById(restauranteId, produtoId).orElseThrow(() ->					
					 new FotoProdutoNaoEncontradaException(String.format(MSG_FOTO_NAO_ENCONTRADA, produtoId, restauranteId)));
	}
}
