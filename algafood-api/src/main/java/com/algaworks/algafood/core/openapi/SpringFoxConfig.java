package com.algaworks.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
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
	
	private static String MESSAGE_BAD_REQUEST = "Consulta inválida";
	private static String MESSAGE_INTERNAL_SERVER_ERROR = "Erro interno do servidor";
	private static String MESSAGE_NOT_ACCEPTABLE = "Recurso não possui representação que pode ser aceita pelo consumidor";
	private static String MESSAGE_UNSUPPORTED_MEDIA_TYPE = "Tipo de midia não suportada";
	
	 @Bean
	  public Docket apiDocket() {
		 
		 var typeResolver = new TypeResolver();
	    return new Docket(DocumentationType.OAS_30)
	        .select()
	          //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	        	//.paths(PathSelectors.ant("/restaurantes/*"))
	        	.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
	        	.paths(PathSelectors.any())
	          .build()
	          .useDefaultResponseMessages(false)
	          .globalResponses(HttpMethod.GET, globalGetResponseMessages())
	          .globalResponses(HttpMethod.POST, globalPostResponseMessages())
	          .globalResponses(HttpMethod.PUT, globalPostResponseMessages())
	          .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
	          .additionalModels(typeResolver.resolve(Problem.class))
	          .apiInfo(apiInfo())
	          .tags(new Tag("Cidades", "Gerência as cidades"));
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
	 
	 private ApiInfo apiInfo() {
		 return new ApiInfoBuilder()
				 .title("Algafood")
				 .description("API aberta para clientes e restaurantes.")
				 .version("1")
				 .contact(new Contact("Vinícius", "https://www.vinicius.templario.com", "vinicius.templario@gmail.com"))
				 .build();
				 
	 }
	 
	 private Consumer<RepresentationBuilder> getProblemaModelReference() {
		    return r -> r.model(m -> m.name("Problema")
		            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
		                    q -> q.name("Problema").namespace("com.algaworks.algafood.api.exceptionhandler")))));
	}
}