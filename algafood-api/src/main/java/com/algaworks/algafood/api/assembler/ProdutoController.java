package com.algaworks.algafood.api.assembler;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.modelo.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;



@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired 
	private ProdutoModelAssembler produtoAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoDisassembler;
	
	@GetMapping
	public Collection<ProdutoModel> listar(){
		return produtoAssembler.toCollectionObject(produtoRepository.findAll());
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long produtoId) {
		Produto produto = cadastroProduto.buscarOuFalhar(produtoId);
		return produtoAssembler.toModel(produto);
	}
	
	@PostMapping
	public ProdutoModel salvar(@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produto = produtoDisassembler.toObjectDomain(produtoInput);
		return produtoAssembler.toModel(cadastroProduto.salvar(produto));
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long produtoId, @Valid @RequestBody ProdutoInput produtoInput) {
		Produto produtoAtual = cadastroProduto.buscarOuFalhar(produtoId);
		produtoDisassembler.toObjectDomain(produtoInput);
		produtoAssembler.copyDomainToObject(produtoInput, produtoAtual);
		return produtoAssembler.toModel(cadastroProduto.salvar(produtoAtual));
	}

}
