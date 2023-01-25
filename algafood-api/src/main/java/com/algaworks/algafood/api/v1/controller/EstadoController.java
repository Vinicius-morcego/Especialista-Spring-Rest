package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(path = "/estados")
public class EstadoController implements EstadoControllerOpenApi{
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
		
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<EstadoModel> listar(){
		List<Estado> todosEstados = estadoRepository.findAll();
		return estadoModelAssembler.toCollectionModel(todosEstados);
	}
	
	@GetMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel buscar(@PathVariable Long estadoId){
		
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId); 
		return estadoModelAssembler.toModel(estado);
		
//		Optional<Estado> estado = estadoRepository.findById(estadoId);
//		if(estado.isPresent()) {
//			return ResponseEntity.ok(estado.get());
//		}
//		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping(path = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel salvar(@RequestBody @Valid EstadoInput estadoInput){
		try {
			Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
			
			return estadoModelAssembler.toModel(cadastroEstado.salvar(estado));
		} catch (EntidadeNaoEncontradaException e) {
			throw new EstadoNaoEncontradoException(e.getMessage());
		}
	}
	
	@PutMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput){
		
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		estadoModelAssembler.copyDomainObject(estadoInput, estadoAtual);
		//BeanUtils.copyProperties(estado, estadoAtual, "id");
		
		return estadoModelAssembler.toModel(cadastroEstado.salvar(estadoAtual));
//		try {
//			
//			
//			Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
//			
//			if(estado != null) {
//				BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
//				cadastroEstado.salvar(estadoAtual.get());
//				return ResponseEntity.ok().body(estadoAtual.get());
//			}
//			return ResponseEntity.notFound().build(); 
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest().build();
//		}
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId){
		cadastroEstado.excluir(estadoId);
//		try {
//			cadastroEstado.excluir(estadoId);
//			return ResponseEntity.noContent().build();
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		} catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
//		}
	}
	

}
