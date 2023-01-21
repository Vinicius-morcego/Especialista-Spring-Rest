package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.api.openapi.controller.ProdutoControllerOpenApi;
import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements ProdutoControllerOpenApi{	
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private ProdutoModelAssembler produtoAssembler;	
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId,
			@RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
		var restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		List<Produto> todosProdutos = null;
		if(incluirInativos) {
			todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
		}else {
			todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
			
		}
		return produtoAssembler.toCollectionModel(todosProdutos)
				.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));
	}	
	
	@GetMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		var produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		return produtoAssembler.toModel(produto);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel salvar(@PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		var restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		var produto = produtoInputDisassembler.toObjectDomain(produtoInput);
		produto.setRestaurante(restaurante);		
		return produtoAssembler.toModel(cadastroProdutoService.salvar(produto));
	}
	
	@PutMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		var produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);					
		produtoAssembler.copyDomainToObject(produtoInput, produto);
		return produtoAssembler.toModel(cadastroProdutoService.salvar(produto));
		
	}

	
}
