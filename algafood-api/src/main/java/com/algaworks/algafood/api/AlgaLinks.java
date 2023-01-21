package com.algaworks.algafood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.GrupoController;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.RestauranteFormaPagamentoController;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.RestauranteResponsavelController;
import com.algaworks.algafood.api.controller.StatusPedidoController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;

@Component
public class AlgaLinks {

	private static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	public Link linkToPedidos(String rel) {
		var filterVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM)
				
				);
		var pedidoUrl = linkTo(PedidoController.class).toUri().toString();
		return Link.of(UriTemplate.of(pedidoUrl, PAGE_VARIABLES.concat(filterVariables)), rel);
	}	
	
	
	
	public Link linkToAbrindoRestaurante(Long codigo, String rel) {		
		return linkTo(methodOn(RestauranteController.class)
				.abrir(codigo)).withRel(rel);
	}
	
	public Link linkToFechandoRestaurante(Long codigo, String rel) {		
		return linkTo(methodOn(RestauranteController.class)
				.fechar(codigo)).withRel(rel);
	}
	
	public Link linkToAtivandoRestaurante(Long codigo, String rel) {		
		return linkTo(methodOn(RestauranteController.class)
				.ativar(codigo)).withRel(rel);
	}
	
	public Link linkToInativandoRestaurante(Long codigo, String rel) {		
		return linkTo(methodOn(RestauranteController.class)
				.inativar(codigo)).withRel(rel);
	}
	public Link linkToConfirmacaoPedido(String codigo, String rel) {		
		return linkTo(methodOn(StatusPedidoController.class)
				.confirmar(codigo)).withRel(rel);
	}
	
	public Link linkToEntregaPedido(String codigo, String rel) {		
		return linkTo(methodOn(StatusPedidoController.class)
				.entregue(codigo)).withRel(rel);
	}
	
	public Link linkToCancelamentoPedido(String codigo, String rel) {		
		return linkTo(methodOn(StatusPedidoController.class)
				.cancelar(codigo)).withRel(rel);
	}
	
	public Link linkToRestaurantes(Long restauranteId, String rel) {		
		return linkTo(methodOn(RestauranteController.class)
				.buscar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestaurantes(Long restauranteId) {
		return linkToRestaurantes(restauranteId, IanaLinkRelations.SELF_VALUE);
	}	
	
	public Link linkToRestaurantes(String rel) {
		var restauranteUrl = linkTo(RestauranteController.class).withRel(rel).toUri().toString();
		var templateVariables = new TemplateVariables(
				new TemplateVariable("projecao", VariableType.REQUEST_PARAM)		
			);
		return Link.of(UriTemplate.of(restauranteUrl, templateVariables), rel);
	}
	
	public Link linkToRestauranteFormasPagamentoDesassociacao(Long restauranteId, 
			Long formaPagamentoId, String rel) {		
		return linkTo(methodOn(RestauranteFormaPagamentoController.class)
				.desassociar(restauranteId, formaPagamentoId)).withRel(rel);
	}
	
	public Link linkToRestauranteFormasPagamentoAssociacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class)
				.associar(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToUsuarios(Long clienteId, String rel) {		
		return linkTo(methodOn(UsuarioController.class)
				.buscar(clienteId))
				.withRel(rel);
	}
	
	public Link linkToUsuarios(Long clienteId) {		
		return linkToUsuarios(clienteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToUsuarios(String rel) {		
		return linkTo(UsuarioController.class).withRel(rel);
	}
	
	public Link linkToFormaPagamentos(Long restauranteId, String rel) {		
		return linkTo(methodOn(RestauranteFormaPagamentoController.class)
				.listar(restauranteId)).withRel(rel);
	}
	
	public Link linkToFormaPagamentos(Long restauranteId) {		
		return linkToFormaPagamentos(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToFormaPagamentos(String rel) {		
		return linkTo(FormaPagamentoController.class).withRel(rel);
	}
	
	public Link linkToCidades(Long cidadeId, String rel) {		
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
	}
	
	public Link linkToCidades(Long cidadeId) {
		return linkToCidades(cidadeId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class)
				.buscar(restauranteId, produtoId))
				.withRel(rel);
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId) {
		return linkToProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToProdutos(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class)
				.listar(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToProdutos(Long restauranteId) {
		return linkToProdutos(restauranteId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCozinhas(Long cozinhaId, String rel) {		
		return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
	}
	
	public Link linkToCozinhas(Long cozinhaId) {
		return linkToCozinhas(cozinhaId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCozinhas(String rel) {		
		return linkTo(CozinhaController.class).withRel(rel);
	}
	
	public Link linkToEstados(Long estadoId, String rel) {		
		return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
	}
	
	public Link linkToEstados(Long estadoId) {
		return linkToEstados(estadoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToEstados(String rel) {		
		return linkTo(EstadoController.class).withRel(rel);
	}
	
	public Link linkToGrupos(Long grupoId, String rel) {		
		return linkTo(methodOn(GrupoController.class).buscar(grupoId)).withRel(rel);
	}
	
	public Link linkToGrupos(Long grupoId) {
		return linkToGrupos(grupoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGrupos(String rel) {		
		return linkTo(GrupoController.class).withRel(rel);
	}
	
	public Link linkToGruposUsuario(Long usuarioId, String rel) {		
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}
	
	public Link linkToGruposUsuario(Long usuarioId) {
		return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGruposUsuario(String rel) {		
		return linkTo(UsuarioGrupoController.class).withRel(rel);
	}
	
	public Link linkToRestauranteResponsavel(Long restauranteId, String rel) {		
		return linkTo(methodOn(RestauranteResponsavelController.class)
				.listar(restauranteId)).withRel(rel);		
	}
	
	public Link linkToRestauranteResponsavel(Long restauranteId) {
		return linkToRestauranteResponsavel(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteResponsavel(String rel) {		
		return linkTo(RestauranteResponsavelController.class).withRel(rel);
	}
	
	public Link linkToRestauranteResponsavelDesassociar(Long restauranteId, Long usuarioId, String rel) {
		return linkTo(methodOn(RestauranteResponsavelController.class)
				.desassociar(restauranteId, usuarioId)).withRel(rel);
	}
	
	public Link linkToRestauranteResponsavelAssociar(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteResponsavelController.class)
				.associar(restauranteId, null)).withRel(rel);
	}
	
}
