package com.algaworks.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

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
	          .apiInfo(apiInfo())
	          .tags(new Tag("Cidades", "Gerência as cidades"));
	  }	 
	
	 
	 private List<Response> globalGetResponseMessages(){
		 
		 return Arrays.asList(
				 new ResponseBuilder()				 
				 	.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				 	.description("Erro interno do servidor")
				 	.build(),				 	
				 	new ResponseBuilder()
				 	.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
				 	.description("Recurso não possui representação que pode ser aceita pelo consumidor")
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
