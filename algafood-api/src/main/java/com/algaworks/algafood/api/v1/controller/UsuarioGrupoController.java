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

import com.algaworks.algafood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi{
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private GrupoModelAssembler grupoAssembler;	
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<GrupoModel>  listar(@PathVariable Long usuarioId){
		
		var usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		CollectionModel<GrupoModel> gruposModel =	
				grupoAssembler.toCollectionModel(usuario.getGrupos())
				.removeLinks()
				.add(algaLinks.linkToUsuarioPermissoesAssociar(usuario.getId(), "associar"));
		
		gruposModel.getContent().forEach(grupoModel -> {
			grupoModel.add(algaLinks.linkToUsuarioPermissoesDesassociar(usuario.getId(), grupoModel.getId(), "desassociar"));
		});
		return gruposModel;
		
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.desassociar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.associar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}

}
