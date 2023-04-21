package com.algaworks.algafood.api.v1.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.v1.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.modelo.FotoProduto;
import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.domain.service.FotoStorageService.FotoRecuperada;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi{

	@Autowired
	CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
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
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	//@GetMapping
	public FotoProdutoModel listar(@PathVariable Long produtoId, @PathVariable Long restauranteId) {
			FotoProduto foto = catalogoFotoProduto.buscarOuFalhar(produtoId, restauranteId);			
			return fotoProdutoModelAssembler.toModel(foto);
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerFotoProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		catalogoFotoProduto.removerFotoProduto(produtoId, restauranteId);		
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long produtoId, 
			@PathVariable Long restauranteId, @RequestHeader(name = "accept") String acceptHeader)
			throws HttpMediaTypeNotAcceptableException {
		
		try {
			FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(produtoId, restauranteId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader); 
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
			
			if(fotoRecuperada.temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			}else {
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));				
			}
			
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
