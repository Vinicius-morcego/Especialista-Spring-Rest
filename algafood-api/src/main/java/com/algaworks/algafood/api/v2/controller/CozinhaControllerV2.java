package com.algaworks.algafood.api.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algaworks.algafood.api.v2.assembler.CozinhaInputDisassemblerV2;
import com.algaworks.algafood.api.v2.assembler.CozinhaModelAssemblerV2;
import com.algaworks.algafood.api.v2.model.CozinhaModelV2;
import com.algaworks.algafood.api.v2.model.input.CozinhaInputV2;
import com.algaworks.algafood.api.v2.openapi.controller.CozinhaControllerV2OpenApi;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v2/cozinhas")
public class CozinhaControllerV2 implements CozinhaControllerV2OpenApi{
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaModelAssemblerV2 cozinhaModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedModelAssembler;
	
	@Autowired
	private CozinhaInputDisassemblerV2 cozinhaInputDisassembler;	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	public PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 2) Pageable pageable){
		var cozinhasPage = cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaModelV2> cozinhaPagedModel = pagedModelAssembler
				.toModel(cozinhasPage, cozinhaModelAssembler);
		return cozinhaPagedModel;
	} 
	
	
	@GetMapping(path = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaModelV2 buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinhaService.busarOuFalhar(cozinhaId); 
		
		return cozinhaModelAssembler.toModel(cozinha);
		
//		Optional<Cozinha> cozinha =  cozinhaRepository.findById(cozinhaId);
//		
//		if(cozinha.isPresent()) {
//			return ResponseEntity.ok(cozinha.get());
//		}
//		
//		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping(path = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModelV2 adicionar(@RequestBody @Valid CozinhaInputV2 cozinhaInput) {
		
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
	}
	
	@PutMapping(path = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaModelV2 atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputV2 cozinhaInput){
		
		Cozinha cozinhaAtual = cadastroCozinhaService.busarOuFalhar(cozinhaId);
		
		cozinhaModelAssembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		
		
		//BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		
		return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinhaAtual));
		
//		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
//	
//		if(cozinhaAtual.isPresent()) {
//			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
//			
//			Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
//			return ResponseEntity.ok(cozinhaSalva);
//		}
//		
//		return ResponseEntity.notFound().build();
		
	}
	
//	@DeleteMapping("/{cozinhaId}")
//	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
//		try {
//			
//			cadastroCozinhaService.excluir(cozinhaId);
//			return ResponseEntity.noContent().build();
//			
//		}catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		} catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//		
//				
//	}
	
	@DeleteMapping(path = "/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId){
			try {
				cadastroCozinhaService.excluir(cozinhaId);
			} catch (EmptyResultDataAccessException e) {
				throw new CozinhaNaoEncontradaException(e.getMessage());
			} catch(DataIntegrityViolationException e){
				throw new EntidadeEmUsoException(e.getMessage());
			}
			
			
				
	}

}
