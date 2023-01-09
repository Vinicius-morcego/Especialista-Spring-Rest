package com.algaworks.algafood.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.modelo.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "/cidades")
public class CidadeController implements CidadeControllerOpenApi{

	@Autowired 
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired 
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CidadeModel> listar(){
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
	}	
	
	@GetMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeModel buscar(@PathVariable Long cidadeId){
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);	
		
		CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
		
//		cidadeModel.add(linkTo(CidadeController.class)
//				.slash(cidadeModel.getId()).withSelfRel());
		cidadeModel.add(linkTo(methodOn(CidadeController.class, cidadeModel.getId())
				.buscar(cidadeModel.getId())).withSelfRel());
		
//		cidadeModel.add(linkTo(CidadeController.class)
//				.withRel("cidades"));
		
		cidadeModel.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));
		
//		cidadeModel.getEstado().add(linkTo(EstadoController.class)
//				.slash(cidadeModel.getEstado().getId()).withSelfRel());
		cidadeModel.getEstado().add(linkTo(methodOn(EstadoController.class)
				.buscar(cidadeModel.getEstado().getId())).withSelfRel());
		return cidadeModel;
//		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
//		if(cidade.isPresent()) {
//			return ResponseEntity.ok(cidade.get());
//		}
//		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping(path = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	
	@PutMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
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
