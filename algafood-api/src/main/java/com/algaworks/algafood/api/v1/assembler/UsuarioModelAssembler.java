package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.UsuarioController;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.modelo.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel>{
	

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
	}
	
	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
		modelMapper.map(usuario, usuarioModel);
		
		if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
			usuarioModel.add(algaLinks.linkToUsuarios("usuarios"));		
			usuarioModel.add(algaLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));		
		}
		return usuarioModel;
	}
	
	public void copyDomainToObject(UsuarioInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
	
	public void updatePasswordToObject(SenhaInput usuarioInputUpdate, Usuario usuario) {
		modelMapper.map(usuarioInputUpdate, usuario);
	}
	
	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		
		return super.toCollectionModel(entities).add(algaLinks.linkToUsuarios("clientes"));
	}
	
//	public Collection<UsuarioModel> toCollectionObject(Collection<Usuario> usuarios){		
//		return usuarios.stream()
//				.map(usuario -> toModel(usuario))
//				.collect(Collectors.toList());
//	}

}
