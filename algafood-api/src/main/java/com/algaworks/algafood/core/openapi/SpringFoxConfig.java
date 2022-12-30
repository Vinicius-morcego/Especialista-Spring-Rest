package com.algaworks.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig{
	
	private static String MESSAGE_BAD_REQUEST = "Consulta inválida";
	private static String MESSAGE_INTERNAL_SERVER_ERROR = "Erro interno do servidor";
	private static String MESSAGE_NOT_ACCEPTABLE = "Recuso não possui representação que pode ser aceita pelo consumidor";
	private static String MESSAGE_UNSUPPORTED_MEDIA_TYPE = "Tipo de midia não suportada";
	
	 @Bean
	  public Docket apiDocket() {
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
	          .apiInfo(apiInfo())
	          .tags(new Tag("Cidades", "Gerência as cidades"));
	  }	 
	
	 
	 private List<Response> globalGetResponseMessages(){
		 
		 return Arrays.asList(
				 new ResponseBuilder()				 
				 	.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				 	.description(MESSAGE_INTERNAL_SERVER_ERROR)
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
				 	.build(),
				 	new ResponseBuilder()				 	
				 	.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				 	.description(MESSAGE_INTERNAL_SERVER_ERROR)
				 	.build(),				 	
				 	new ResponseBuilder()
				 	.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
				 	.description(MESSAGE_NOT_ACCEPTABLE)
				 	.build(),
				 	new ResponseBuilder()				 	
				 	.code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
				 	.description(MESSAGE_UNSUPPORTED_MEDIA_TYPE)
				 	.build()
				 );
		 
	 }
	 
	 private List<Response> globalDeleteResponseMessages(){
		 
		 return Arrays.asList(
				 new ResponseBuilder()				 
				 	.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				 	.description(MESSAGE_INTERNAL_SERVER_ERROR)
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
}
