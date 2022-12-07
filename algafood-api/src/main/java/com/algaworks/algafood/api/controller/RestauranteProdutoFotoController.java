package com.algaworks.algafood.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.modelo.FotoProduto;
import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.infrastructure.storage.StorageException;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
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
	
}
