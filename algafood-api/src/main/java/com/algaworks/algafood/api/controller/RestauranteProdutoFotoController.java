package com.algaworks.algafood.api.controller;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.modelo.FotoProduto;
import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.FotoStorageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) throws IOException {
		
				Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
				
				MultipartFile arquivo = fotoProdutoInput.getArquivo();
				
				FotoProduto fotoProduto = new FotoProduto();
				
				fotoProduto.setProduto(produto);
				fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
				fotoProduto.setContentType(arquivo.getContentType());
				fotoProduto.setTamanho(arquivo.getSize());
				fotoProduto.setNomeArquivo(arquivo.getOriginalFilename());
				
				FotoProduto fotoSalva;
				fotoSalva = catalogoFotoProduto.salvar(fotoProduto, arquivo.getInputStream());
				return fotoProdutoModelAssembler.toModel(fotoSalva);
			
		
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoModel listar(@PathVariable Long produtoId, @PathVariable Long restauranteId) {
			FotoProduto foto = catalogoFotoProduto.buscarOuFalhar(produtoId, restauranteId);			
			return fotoProdutoModelAssembler.toModel(foto);
	}
	
	@GetMapping
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long produtoId, 
			@PathVariable Long restauranteId, @RequestHeader(name = "accept") String acceptHeader)
			throws HttpMediaTypeNotAcceptableException {
		
		try {
			FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(produtoId, restauranteId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader); 
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			InputStream input = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
			
			return ResponseEntity.ok()
					.contentType(mediaTypeFoto)
					.body(new InputStreamResource(input));
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) 
			throws HttpMediaTypeNotAcceptableException {
		
		
			boolean compativel = mediaTypesAceitas.stream()
					.anyMatch(mediaTypeAceita -> mediaTypeAceita
					.isCompatibleWith(mediaTypeFoto));
			
			if(!compativel)
				throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		
	}
	
}
