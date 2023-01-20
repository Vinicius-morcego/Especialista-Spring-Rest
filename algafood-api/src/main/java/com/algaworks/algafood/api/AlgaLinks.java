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
	
	public Link linkToPedidos() {
		var filterVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM)
				
				);
		var pedidoUrl = linkTo(PedidoController.class).toUri().toString();
		return Link.of(UriTemplate.of(pedidoUrl, PAGE_VARIABLES.concat(filterVariables)), "pedidos");
	}	
	
	public Link linkToConfirmacaoPedido(String codigo, String rel) {
		var confirmarUrl = linkTo(methodOn(StatusPedidoController.class)
				.confirmar(codigo)).withRel(rel).toUri().toString();
		return Link.of(confirmarUrl);
	}
	
	public Link linkToEntregaPedido(String codigo, String rel) {
		var entregarUrl = linkTo(methodOn(StatusPedidoController.class)
				.entregue(codigo)).withRel(rel).toUri().toString();
		return Link.of(entregarUrl);
	}
	
	public Link linkToCancelamentoPedido(String codigo, String rel) {
		var cancelarUrl = linkTo(methodOn(StatusPedidoController.class)
				.cancelar(codigo)).withRel(rel).toUri().toString();
		return Link.of(cancelarUrl);
	}
	
	public Link linkToRestaurantes(Long restauranteId, String rel) {
		var restauranteUrl = linkTo(methodOn(RestauranteController.class)
				.buscar(restauranteId)).withRel(rel)
				.toUri().toString();
		return Link.of(restauranteUrl);
	}
	
	public Link linkToRestaurantes(Long restauranteId) {
		return linkToRestaurantes(restauranteId, IanaLinkRelations.SELF_VALUE);
	}	
	
	public Link linkToRestaurantes(String rel) {
		var restauranteUrl = linkTo(RestauranteController.class).withRel(rel).toUri().toString();
		return Link.of(restauranteUrl);
	}
	
	public Link linkToUsuarios(Long clienteId, String rel) {
		var clienteUrl = linkTo(methodOn(UsuarioController.class)
				.buscar(clienteId))
				.withRel(rel).toUri().toString();
		return Link.of(clienteUrl);
	}
	
	public Link linkToUsuarios(Long clienteId) {		
		return linkToUsuarios(clienteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToUsuarios(String rel) {
		var clienteUrl = linkTo(UsuarioController.class).withRel(rel).toUri().toString();
		return Link.of(clienteUrl);
	}
	
	public Link linkToFormaPagamentos(Long restauranteId, String rel) {
		var formaPagamentosUrl = linkTo(methodOn(RestauranteFormaPagamentoController.class)
				.listar(restauranteId)).withRel(rel).toUri().toString();
		return Link.of(formaPagamentosUrl);
	}
	
	public Link linkToFormaPagamentos(Long restauranteId) {		
		return linkToFormaPagamentos(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToFormaPagamentos(String rel) {
		var formaPagamentosUrl = linkTo(FormaPagamentoController.class).withRel(rel).toUri().toString();
		return Link.of(formaPagamentosUrl);
	}
	
	public Link linkToCidades(Long cidadeId, String rel) {
		var cidadesUrl = linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel).toUri().toString();
		return Link.of(cidadesUrl);
	}
	
	public Link linkToCidades(Long cidadeId) {
		return linkToCidades(cidadeId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCidades(String rel) {
		var cidadeUrl = linkTo(CidadeController.class).withRel(rel).toUri().toString();
		return Link.of(cidadeUrl);
	}
	public Link linkToRestauranteProduto(Long restauranteId, Long produtoId, String rel) {
		var itensUrl = linkTo(methodOn(RestauranteProdutoController.class)
				.buscar(restauranteId, produtoId)).withRel(rel).toUri().toString();
		return Link.of(itensUrl);
	}
	
	public Link linkToRestauranteProduto(Long restauranteId, Long produtoId) {
		return linkToRestauranteProduto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteProduto(String rel) {
		var produtoUrl = linkTo(RestauranteProdutoController.class).withRel(rel).toUri().toString();
		return Link.of(produtoUrl);
	}
	
	public Link linkToCozinhas(Long cozinhaId, String rel) {
		var cozinhaUrl = linkTo(methodOn(CozinhaController.class)
				.buscar(cozinhaId))
				.withRel(rel).toUri().toString();
		return Link.of(cozinhaUrl);
	}
	
	public Link linkToCozinhas(Long cozinhaId) {
		return linkToCozinhas(cozinhaId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCozinhas(String rel) {
		var cozinhaUrl = linkTo(CozinhaController.class).withRel(rel).toUri().toString();
		return Link.of(cozinhaUrl);
	}
	
	public Link linkToEstados(Long estadoId, String rel) {
		var estadoUrl = linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel).toUri().toString();
		return Link.of(estadoUrl);
	}
	
	public Link linkToEstados(Long estadoId) {
		return linkToEstados(estadoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToEstados(String rel) {
		var estadoUrl = linkTo(EstadoController.class).withRel(rel).toUri().toString();
		return Link.of(estadoUrl);
	}
	
	public Link linkToGrupos(Long grupoId, String rel) {
		var grupoUrl = linkTo(methodOn(GrupoController.class).buscar(grupoId)).withRel(rel).toUri().toString();
		return Link.of(grupoUrl);
	}
	
	public Link linkToGrupos(Long grupoId) {
		return linkToGrupos(grupoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGrupos(String rel) {
		var grupoUrl = linkTo(GrupoController.class).withRel(rel).toUri().toString();
		return Link.of(grupoUrl);
	}
	
	public Link linkToGruposUsuario(Long usuarioId, String rel) {
		var grupoUsuarioUrl = linkTo(methodOn(UsuarioGrupoController.class)
				.listar(usuarioId)).withRel(rel).toUri().toString();
		return Link.of(grupoUsuarioUrl);
	}
	
	public Link linkToGruposUsuario(Long usuarioId) {
		return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGruposUsuario(String rel) {
		var grupoUsuarioUrl = linkTo(UsuarioGrupoController.class).withRel(rel).toUri().toString();
		return Link.of(grupoUsuarioUrl);
	}
	
	public Link linkToRestauranteResponsavel(Long restauranteId, String rel) {
		var responsavelUrl = linkTo(methodOn(RestauranteResponsavelController.class)
				.listar(restauranteId)).withRel(rel).toUri().toString();
		return Link.of(responsavelUrl);		
	}
	
	public Link linkToRestauranteResponsavel(Long restauranteId) {
		return linkToRestauranteResponsavel(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteResponsavel(String rel) {
		var responsavelUrl = linkTo(RestauranteResponsavelController.class).withRel(rel).toUri().toString();
		return Link.of(responsavelUrl);
	}
	
}
