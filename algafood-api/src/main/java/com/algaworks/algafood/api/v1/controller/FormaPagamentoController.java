package com.algaworks.algafood.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.algaworks.algafood.api.v1.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.v1.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.v1.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.domain.modelo.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;


@RestController
@RequestMapping(path = "/v1/formaPagamentos")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi{
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;
	
	@Autowired
	private FormaPagamentoAssembler formaPagamentoAssembler;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> consultar(ServletWebRequest request){	
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getUltimaDataAtualizacao();
		String eTag = "0";
		if(dataUltimaAtualizacao != null)
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		
		if(request.checkNotModified(eTag))
			return null;
		
		CollectionModel<FormaPagamentoModel> formaPagamentosModel = formaPagamentoAssembler
				.toCollectionModel(formaPagamentoRepository.findAll());
		return ResponseEntity.ok()
				//.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				//.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				//.cacheControl(CacheControl.noCache())
				//.cacheControl(CacheControl.noStore())
				.eTag(eTag)
				.body(formaPagamentosModel);
	}
	
	@GetMapping(path = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getUltimaDataAtualizacaoById(formaPagamentoId);
		if(dataUltimaAtualizacao != null)
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		
		if(request.checkNotModified(eTag))
			return null;
		
		
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		FormaPagamentoModel formaPagamentoModel = formaPagamentoAssembler.toModel(formaPagamento);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(formaPagamentoModel);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
		
		return formaPagamentoAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.excluir(formaPagamentoId);
	}
	
}
