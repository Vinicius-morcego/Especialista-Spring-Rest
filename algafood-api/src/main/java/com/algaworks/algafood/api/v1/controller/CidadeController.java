package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.api.v1.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.core.web.AlgaMediaTypes;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.modelo.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "/v1/cidades")
public class CidadeController implements CidadeControllerOpenApi{ 

	private static final String VERSIONAMENTO_POR_MEDIA_TYPE = AlgaMediaTypes.V1_APPLICATION_JSON_VALUE;
	private static final String VERSIONAMENTO_POR_URI = MediaType.APPLICATION_JSON_VALUE;

	@Autowired 
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired 
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@GetMapping(produces = VERSIONAMENTO_POR_URI)
	public CollectionModel<CidadeModel> listar(){
		List<Cidade> todasCidades = cidadeRepository.findAll();
		return cidadeModelAssembler.toCollectionModel(todasCidades);		
	}	
	
	@GetMapping(path = "/{cidadeId}", produces = VERSIONAMENTO_POR_URI)
	public CidadeModel buscar(@PathVariable Long cidadeId){
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
//		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
//		if(cidade.isPresent()) {
//			return ResponseEntity.ok(cidade.get());
//		}
//		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping(path = "/salvar", produces = VERSIONAMENTO_POR_URI)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel salvar(@RequestBody @Valid CidadeInput cidadeInput){
		
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			CidadeModel cidadeModel =  cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade)); 
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
			
			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
			
	}
	
	
	@PutMapping(path = "/{cidadeId}", produces = VERSIONAMENTO_POR_URI)
	public CidadeModel atualizar(
			@PathVariable Long cidadeId,			 
			@RequestBody @Valid CidadeInput cidadeInput){
	    try {
	    	Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
	    	cidadeModelAssembler.copyToDomainObject(cidadeInput, cidadeAtual);
	    	//BeanUtils.copyProperties(cidade, cidadeAtual, "id");
	    	
	    	return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
	    	
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	    
	    
//	    try {
//			if(cidadeAtual != null) {
//				BeanUtils.copyProperties(cidade, cidadeAtual, "id");
//				cadastroCidade.salvar(cidadeAtual);
//				return ResponseEntity.ok(cidadeAtual);
//			}
//			
//			return ResponseEntity.notFound().build();
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest().build();
//		}
	}	
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(			 
			@PathVariable Long cidadeId){
		try {
			
			cadastroCidade.remover(cidadeId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(e.getMessage());
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(e.getMessage());
		}
		
		
//		try {
//			cadastroCidade.remover(cidadeId);
//			return ResponseEntity.noContent().build();
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		} catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//		
	}
	
}
