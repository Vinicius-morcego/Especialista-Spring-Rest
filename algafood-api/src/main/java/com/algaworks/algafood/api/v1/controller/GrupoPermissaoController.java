package com.algaworks.algafood.api.v1.controller;

import java.util.List;

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
import com.algaworks.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi{	
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoAssembler;	
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId){
		var grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		CollectionModel<PermissaoModel> grupoPermissoesModel = 
				permissaoAssembler.toCollectionModel(grupo.getPermissoes());
		grupoPermissoesModel.getContent().forEach(grupoPermissaoModel -> {
			grupoPermissaoModel.add(algaLinks.linkToPermissaoGrupoDesassociar(grupo.getId(), "desassociar"));
		});
		
		grupoPermissoesModel.add(algaLinks.linkToPermissaoGrupoAssociar(grupo.getId(), "associar"));
		
		return grupoPermissoesModel;
		
					
	}	
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.associar(grupoId, permissaoId);		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.desassociar(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
}
