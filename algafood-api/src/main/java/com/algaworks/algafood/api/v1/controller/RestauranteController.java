package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.RestauranteApenasNomeModelAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteBasicoModelAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.modelo.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

//@CrossOrigin(maxAge = 10)
@RestController
@RequestMapping(path = "/v1/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi{

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;
	
	@Autowired
	private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;
	
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;
	
	//@JsonView(RestauranteView.Resumo.class)
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<RestauranteBasicoModel> listar(){		
		//BeanUtils.copyProperties(Restaurante.class, RestauranteModel.class);
		
		return restauranteBasicoModelAssembler
				.toCollectionModel(restauranteRepository.findAll());		 
	}

	//@JsonView(RestauranteView.ApenasNome.class)
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping(params = "projecao=apenas-nome") 
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes(){
		return restauranteApenasNomeModelAssembler
				.toCollectionModel(restauranteRepository.findAll());
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping(path = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestauranteBasicoModel buscar(@PathVariable Long restauranteId) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);		
		return restauranteBasicoModelAssembler.toModel(restaurante);
//		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
//		if(restaurante.isPresent()) {
//			return ResponseEntity.ok(restaurante.get());
//		}
//		return ResponseEntity.notFound().build();

	}	
	
	@CheckSecurity.Restaurantes.PodeEditar
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel salvar(@RequestBody @Valid RestauranteInput restauranteInput){
			try {
				Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
				return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
				
			} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
				throw new NegocioException(e.getMessage(), e);
			}
			
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@PutMapping(path = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestauranteModel atualizar(
			@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput){
		
		
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
	
	@CheckSecurity.Restaurantes.PodeEditar
	//PUT /restaurantes/{id}/ativar
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
		return ResponseEntity.noContent().build(); 
	}
	
	//DELETE /restaurantes/{id}/inativar
	@CheckSecurity.Restaurantes.PodeEditar
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);	
		return ResponseEntity.noContent().build();
	}	
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);			
		} catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
			
		}
		
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
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
	@CheckSecurity.Restaurantes.PodeEditar
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);		
		return ResponseEntity.noContent().build();		
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long restauranteId) {
			try {
				cadastroRestaurante.excluir(restauranteId);
				
			} catch (EmptyResultDataAccessException e) {
				throw new RestauranteNaoEncontradoException(e.getMessage());
			} catch(DataIntegrityViolationException e) {
				throw new EntidadeEmUsoException(String.format("Restaurante de código %d está em uso e não pode ser excluído", restauranteId));
			}
		
	}
	
}
