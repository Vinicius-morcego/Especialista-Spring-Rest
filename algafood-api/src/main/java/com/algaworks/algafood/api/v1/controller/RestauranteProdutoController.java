package com.algaworks.algafood.api.v1.controller;

import java.util.List;

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

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;
import com.algaworks.algafood.api.v1.openapi.controller.ProdutoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos")
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
	
	@CheckSecurity.Restaurantes.PodeConsultar
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
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		var produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		return produtoAssembler.toModel(produto);
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel salvar(@PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		var restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		var produto = produtoInputDisassembler.toObjectDomain(produtoInput);
		produto.setRestaurante(restaurante);		
		return produtoAssembler.toModel(cadastroProdutoService.salvar(produto));
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		var produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);					
		produtoAssembler.copyDomainToObject(produtoInput, produto);
		return produtoAssembler.toModel(cadastroProdutoService.salvar(produto));
		
	}

	
}
