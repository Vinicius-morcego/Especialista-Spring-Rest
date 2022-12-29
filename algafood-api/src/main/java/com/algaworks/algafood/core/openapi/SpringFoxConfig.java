package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tags;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
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
	          .apiInfo(apiInfo())
	          .tags(new Tag("Cidades", "Gerência as cidades"));
	  }	 
	
	 public ApiInfo apiInfo() {
		 return new ApiInfoBuilder()
				 .title("Algafood")
				 .description("API aberta para clientes e restaurantes.")
				 .version("1")
				 .contact(new Contact("Vinícius", "https://www.vinicius.templario.com", "vinicius.templario@gmail.com"))
				 .build();
				 
	 }
}
