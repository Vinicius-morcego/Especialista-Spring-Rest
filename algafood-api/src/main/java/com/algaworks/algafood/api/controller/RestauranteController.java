package com.algaworks.algafood.api.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.model.view.RestauranteView;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;

@CrossOrigin(maxAge = 10)
@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;
	
	@JsonView(RestauranteView.Resumo.class)
	@GetMapping
	public List<RestauranteModel> listar(){		
		//BeanUtils.copyProperties(Restaurante.class, RestauranteModel.class);
		
		return restauranteModelAssembler
				.toCollectionModel(restauranteRepository.findAll());		 
	}
	
	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public Collection<RestauranteModel> listarApenasNomes(){
		return listar();
	}
	
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);		
		return restauranteModelAssembler.toModel(restaurante);
//		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
//		if(restaurante.isPresent()) {
//			return ResponseEntity.ok(restaurante.get());
//		}
//		return ResponseEntity.notFound().build();

	}	
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel salvar(@RequestBody @Valid RestauranteInput restauranteInput){
			try {
				Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
				return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
				
			} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
				throw new NegocioException(e.getMessage(), e);
			}
			
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput){
		
		
		try {
			//Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);			
			
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
			
			restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
			
//			BeanUtils.copyProperties(restaurante, restauranteAtual, 
//					"id", "formasPagamento", "endereco", "dataCadastro", "produtos");
			
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
		
//		try {
//			Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
//			if(restauranteAtual.isPresent()) {
//				BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id", "formasPagamento", "endereco", 
//						"dataCadastro", "produtos");
//				cadastroRestaurante.salvar(restauranteAtual.get());
//				return ResponseEntity.ok(restauranteAtual.get());
//			}
//			return ResponseEntity.notFound().build();
//		
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.badRequest().build();
//		}
		
	
}
	
//	@PatchMapping("/{restauranteId}")
//	public RestauranteModel atualizarParcial(@PathVariable Long restauranteId,
//			@RequestBody @Valid Map<String, Object> campos, HttpServletRequest request){
//
//		
//		try {
//			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
//			
//			merge(campos, restauranteAtual, request);
//			
//			validate(restauranteAtual, "restaurante");
//			
//			return toModel(atualizar(restauranteId, restauranteAtual));
//			
//		} catch (RestauranteNaoEncontradoException e) {
//			throw new NegocioException(e.getMessage(), e);
//		}
		
		
//		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
//
//		merge(campos, restauranteAtual.get());
//		
//		atualizar(restauranteId, restauranteAtual.get());
		
//	return ResponseEntity.ok().build();
//	}

//	private void validate(Restaurante restaurante, String objectName) {
//		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
//		
//		validator.validate(restaurante, bindingResult);
//		
//		if(bindingResult.hasErrors()) {
//			throw new ValidacaoException(bindingResult);
//		}
//		
//	}
//
//	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
//		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
//		
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//			
//			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
//			
//			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//				field.setAccessible(true);
//				
//				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//				
//				ReflectionUtils.setField(field, restauranteDestino, novoValor);
//				System.out.println(nomePropriedade+ " = "+valorPropriedade + " = "+novoValor);
//			});
//			
//		} catch (IllegalArgumentException e) {
//			Throwable rootCause = ExceptionUtils.getRootCause(e);
//			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
//		}
//		
//	}
	
	//PUT /restaurantes/{id}/ativar
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
		
	}
	
	//DELETE /restaurantes/{id}/inativar
	
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);		
	}	
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);		
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);			
		} catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
			
		}
		
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.inativar(restauranteIds);	
		} catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
			
		}
	}
	
	//DELETE /restaurantes/{id}/inativar
	
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);
		
	}
	
	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void excluir(@PathVariable Long restauranteId) {
			try {
				cadastroRestaurante.excluir(restauranteId);
				
			} catch (EmptyResultDataAccessException e) {
				throw new RestauranteNaoEncontradoException(e.getMessage());
			} catch(DataIntegrityViolationException e) {
				throw new EntidadeEmUsoException(String.format("Restaurante de código %d está em uso e não pode ser excluído", restauranteId));
			}
		
	}
	
}
