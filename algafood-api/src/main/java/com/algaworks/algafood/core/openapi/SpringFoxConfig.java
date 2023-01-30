package com.algaworks.algafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.openapi.model.CidadesModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.EstadosModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.GruposModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.LinksModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.PedidosResumoModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.PermissoesModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.ProdutosModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.RestaurantesBasicoModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.UsuariosModelOpenApi;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.CozinhaModelV2;
import com.algaworks.algafood.api.v2.openapi.model.CidadesModelV2OpenApi;
import com.algaworks.algafood.api.v2.openapi.model.CozinhasModelV2OpenApi;
import com.algaworks.algafood.api.v2.openapi.model.LinksModelV2OpenApi;
import com.algaworks.algafood.api.v2.openapi.model.PageableModelV2OpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig{
	
	private static String MESSAGE_BAD_REQUEST = "Requesição inválida (erro do cliente)";
	private static String MESSAGE_INTERNAL_SERVER_ERROR = "Erro interno do servidor";
	private static String MESSAGE_NOT_ACCEPTABLE = "Recurso não possui representação que pode ser aceita pelo consumidor";
	private static String MESSAGE_UNSUPPORTED_MEDIA_TYPE = "Tipo de midia não suportada";
	
	 @Bean
	  public Docket apiDocketV1() {
		 
		 var typeResolver = new TypeResolver();
	    return new Docket(DocumentationType.OAS_30)
	    	.groupName("V1")
	        .select()
	          //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	        	//.paths(PathSelectors.ant("/restaurantes/*"))
	        	.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
	        	.paths(PathSelectors.ant("/v1/**"))
	          .build()
	          .useDefaultResponseMessages(false)
	          .globalResponses(HttpMethod.GET, globalGetResponseMessages())
	          .globalResponses(HttpMethod.POST, globalPostResponseMessages())
	          .globalResponses(HttpMethod.PUT, globalPostResponseMessages())
	          .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
//	          .globalRequestParameters(Collections.singletonList(
//	        		new RequestParameterBuilder()
//		        		.name("campos")
//		        		.description("Nome das propriedades para filtrar na resposta, separados por vírgula")
//		        		.in(ParameterType.QUERY)
//		        		.required(true)
//		        		.query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//		        		.build())
//	        		  )
	          .additionalModels(typeResolver.resolve(Problem.class))
	          .ignoredParameterTypes(ServletWebRequest.class, URI.class, URL.class, 
	        		  URLStreamHandler.class, File.class, Resource.class, InputStream.class)
	          .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
	          .directModelSubstitute(Links.class, LinksModelOpenApi.class)
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
	        		  Page.class, CozinhaModel.class), CozinhasModelOpenApi.class))
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
	        		  PagedModel.class, PedidoResumoModel.class), PedidosResumoModelOpenApi.class))	          
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
	        		  CollectionModel.class, CidadeModel.class), CidadesModelOpenApi.class))
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
	        		  CollectionModel.class, EstadoModel.class), EstadosModelOpenApi.class))
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
	        		 CollectionModel.class, FormaPagamentoModel.class), FormasPagamentoModelOpenApi.class))
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
		        		 CollectionModel.class, GrupoModel.class), GruposModelOpenApi.class))
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
		        		 CollectionModel.class, PermissaoModel.class), PermissoesModelOpenApi.class))
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
		        		 CollectionModel.class, ProdutoModel.class), ProdutosModelOpenApi.class))
	          .apiInfo(apiInfoV1())
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
		        		 CollectionModel.class, RestauranteBasicoModel.class), RestaurantesBasicoModelOpenApi.class))
	          .apiInfo(apiInfoV1())	
	          .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
		        		 CollectionModel.class, UsuarioModel.class), UsuariosModelOpenApi.class))
	          .apiInfo(apiInfoV1())
	          .tags( 
	        	  new Tag("Cidades", "Gerência as cidades"), 
	        	  new Tag("Grupos", "Gerência os grupos"),
	        	  new Tag("Cozinhas", "Gerência as cozinhas"),
	        	  new Tag("Formas de pagamento", "Gerência as formas de pagamento"),
	        	  new Tag("Pedidos", "Gerência os pedidos"),
	        	  new Tag("Restaurantes", "Gerência os restaurantes"),
	        	  new Tag("Estados", "Gerência os estados"),
	        	  new Tag("Produtos", "Gerência os produtos do restaurante"),
	        	  new Tag("Usuarios", "Gerência os usuarios"), 
	        	  new Tag("Estatisticas", "Gerência as estatisticas de vendas"),
	        	  new Tag("Permissões", "Gerência as permissões")
	          );
	    		
	  }	 
	 
	 @Bean
		public Docket apiDocketV2() {
			var typeResolver = new TypeResolver();

			return new Docket(DocumentationType.OAS_30)
					.groupName("V2")
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.ant("/v2/**"))
					.build()
					.useDefaultResponseMessages(false)
					.globalResponses(HttpMethod.GET, globalGetResponseMessages())
					.globalResponses(HttpMethod.POST, globalPostResponseMessages())
					.globalResponses(HttpMethod.PUT, globalPostResponseMessages())
					.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
					.alternateTypeRules(AlternateTypeRules.newRule(
							typeResolver.resolve(PagedModel.class, CozinhaModelV2.class),
						    CozinhasModelV2OpenApi.class))
					.alternateTypeRules(AlternateTypeRules.newRule(
					        typeResolver.resolve(CollectionModel.class, CidadeModelV2.class),
					        CidadesModelV2OpenApi.class))
					.ignoredParameterTypes(ServletWebRequest.class,
							URL.class, URI.class, URLStreamHandler.class, Resource.class,
							File.class, InputStream.class)
					.directModelSubstitute(Pageable.class, PageableModelV2OpenApi.class)
					.directModelSubstitute(Links.class, LinksModelV2OpenApi.class)
					.apiInfo(apiInfoV2())
					.tags(new Tag("Cidades", "Gerência as cidades"),
						  new Tag("Cozinhas", "Gerência as cozinhas")
					);
		}
	 
	 	@Bean
		public JacksonModuleRegistrar springFoxJacksonConfig() {
			return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
		}
	
	 
	 private List<Response> globalGetResponseMessages(){
		 
		 return Arrays.asList(
				 new ResponseBuilder()				 
				 	.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				 	.description(MESSAGE_INTERNAL_SERVER_ERROR)
				 	.representation(MediaType.APPLICATION_JSON)
				 	.apply(getProblemaModelReference())
				 	.build(),				 	
				 	new ResponseBuilder()
				 	.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
				 	.description(MESSAGE_NOT_ACCEPTABLE)
				 	.build()
				 );
		 
	 }
	 

	 private List<Response> globalPostResponseMessages(){
		 
		 return Arrays.asList(
				 	new ResponseBuilder()				 	
				 	.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
				 	.description(MESSAGE_BAD_REQUEST)
				 	.representation(MediaType.APPLICATION_JSON)
				 	.apply(getProblemaModelReference())
				 	.build(),
				 	new ResponseBuilder()				 	
				 	.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				 	.description(MESSAGE_INTERNAL_SERVER_ERROR)
				 	.representation(MediaType.APPLICATION_JSON)
				 	.apply(getProblemaModelReference())
				 	.build(),				 	
				 	new ResponseBuilder()
				 	.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
				 	.description(MESSAGE_NOT_ACCEPTABLE)
				 	.build(),
				 	new ResponseBuilder()				 	
				 	.code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
				 	.description(MESSAGE_UNSUPPORTED_MEDIA_TYPE)
				 	.representation(MediaType.APPLICATION_JSON)
				 	.apply(getProblemaModelReference())
				 	.build()
				 );
		 
	 }
	 
	 private List<Response> globalDeleteResponseMessages(){
		 
		 return Arrays.asList(
				 new ResponseBuilder()				 
				 	.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				 	.description(MESSAGE_INTERNAL_SERVER_ERROR)
				 	.representation(MediaType.APPLICATION_JSON)
				 	.apply(getProblemaModelReference())
				 	.build(),				 	
				 	new ResponseBuilder()
				 	.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
				 	.description(MESSAGE_NOT_ACCEPTABLE)
				 	.build()
				 );
		 
	 }
	 
	 private ApiInfo apiInfoV1() {
		 return new ApiInfoBuilder()
				 .title("Algafood - (Depreciada)")
				 .description("API aberta para clientes e restaurantes. <br>"
						 +"<strong>versão da API esta depreciada, sem suporte a partir de 01/02/2023")
				 .version("1")
				 .contact(new Contact("Vinícius", "https://www.vinicius.templario.com", "vinicius.templario@gmail.com"))
				 .build();
				 
	 }
	 
	 private ApiInfo apiInfoV2() {
		 return new ApiInfoBuilder()
				 .title("Algafood")
				 .description("API aberta para clientes e restaurantes.")
				 .version("2")
				 .contact(new Contact("Vinícius", "https://www.vinicius.templario.com", "vinicius.templario@gmail.com"))
				 .build();
				 
	 }
	 
	 private Consumer<RepresentationBuilder> getProblemaModelReference() {
		    return r -> r.model(m -> m.name("Problema")
		            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
		                    q -> q.name("Problema").namespace("com.algaworks.algafood.api.exceptionhandler")))));
	}
}
