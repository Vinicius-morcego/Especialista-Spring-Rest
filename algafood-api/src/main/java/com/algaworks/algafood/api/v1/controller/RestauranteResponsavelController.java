package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteResponsavelControllerOpenApi;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteResponsavelController implements RestauranteResponsavelControllerOpenApi{
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private UsuarioModelAssembler usuarioAssembler;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId){
		var restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		CollectionModel<UsuarioModel> usuariosModel = usuarioAssembler.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks();
		usuariosModel.add(algaLinks.linkToRestauranteResponsavel(restauranteId, "responsaveis"));
		if(algaSecurity.podeGerenciarCadastroRestaurantes()) {
			usuariosModel.add(algaLinks.linkToRestauranteResponsavelAssociar(restauranteId, "associar"));
			usuariosModel.getContent().forEach(usuarioModel -> {
				usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociar(
						restauranteId, usuarioModel.getId(), "desassociar"));
			});			
		}
		
		return usuariosModel;
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {		
		cadastroRestauranteService.desassociar(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {		
		cadastroRestauranteService.associar(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
	

}
