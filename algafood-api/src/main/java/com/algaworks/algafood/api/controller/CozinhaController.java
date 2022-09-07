package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@GetMapping	
	public List<CozinhaModel> listar(){
		return cozinhaModelAssembler.toDomainCollector(cozinhaRepository.findAll());
	} 
	
	//
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
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
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
	}
	
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput){
		
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
	
	@DeleteMapping("/{cozinhaId}")
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
