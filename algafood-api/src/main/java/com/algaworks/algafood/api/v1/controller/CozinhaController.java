package com.algaworks.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.core.security.PodeConsultarCozinhas;
import com.algaworks.algafood.core.security.PodeEditarCozinhas;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/v1/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi{
	
	//private static Logger logger = LoggerFactory.getLogger(CozinhaController.class); 
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedModelAssembler;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	public PagedModel<CozinhaModel> listar(@PageableDefault(size = 2) Pageable pageable){
		log.info("Listando cozinhas com páginas de {} registros.", pageable.getPageSize());
		
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);		
		PagedModel<CozinhaModel> cozinhaPagedModel = pagedModelAssembler
				.toModel(cozinhasPage, cozinhaModelAssembler);
		return cozinhaPagedModel;
	} 
	
	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping(path = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@CheckSecurity.Cozinhas.PodeEditar
	@PostMapping(path = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
	}
	
	@CheckSecurity.Cozinhas.PodeEditar
	@PutMapping(path = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@CheckSecurity.Cozinhas.PodeEditar
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
