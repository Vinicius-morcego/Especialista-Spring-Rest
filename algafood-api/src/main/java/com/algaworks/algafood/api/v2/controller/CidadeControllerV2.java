package com.algaworks.algafood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.algaworks.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.core.web.AlgaMediaTypes;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.modelo.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "/cidades")
public class CidadeControllerV2 { 

	@Autowired 
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeModelAssemblerV2 cidadeModelAssembler;
	
	@Autowired 
	private CidadeInputDisassemblerV2 cidadeInputDisassembler;
	
	@GetMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CollectionModel<CidadeModelV2> listar(){
		List<Cidade> todasCidades = cidadeRepository.findAll();
		return cidadeModelAssembler.toCollectionModel(todasCidades);		
	}	
	
	@GetMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CidadeModelV2 buscar(@PathVariable Long cidadeId){
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
//		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
//		if(cidade.isPresent()) {
//			return ResponseEntity.ok(cidade.get());
//		}
//		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping(path = "/salvar", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 salvar(@RequestBody @Valid CidadeInputV2 cidadeInput){
		
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			CidadeModelV2 cidadeModel =  cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade)); 
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());
			
			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
			
	}
	
	
	@PutMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CidadeModelV2 atualizar(
			@PathVariable Long cidadeId,			 
			@RequestBody @Valid CidadeInputV2 cidadeInput){
	    try {
	    	Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
	    	cidadeModelAssembler.copyToDomainObject(cidadeInput, cidadeAtual);
	    	//BeanUtils.copyProperties(cidade, cidadeAtual, "id");
	    	
	    	return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
	    	
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	
	}
	
}
