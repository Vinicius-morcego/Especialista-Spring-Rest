package com.algaworks.algafood.core.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
				.title("Algafood API")
				.version("v1")
				.description("REST API do Algafood")
				.license(new License()
						.name("Apache 2.0")
						.url("HTTP://springdoc.com"))
					);
				
	}
}
