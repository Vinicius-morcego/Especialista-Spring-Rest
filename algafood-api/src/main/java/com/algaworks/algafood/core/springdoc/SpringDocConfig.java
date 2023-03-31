package com.algaworks.algafood.core.springdoc;

import java.util.Arrays;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@SecurityScheme(name = "security_auth", 
type = SecuritySchemeType.OAUTH2,
flows = @OAuthFlows(authorizationCode = @OAuthFlow(
	authorizationUrl = "$(springdoc.oAuthFlow.authorizationUrl)",
	tokenUrl = "$(springdoc.oAuthFlow.tokenUrl)",
	scopes = {
			@OAuthScope(name = "READ", description = "read scope"),
			@OAuthScope(name = "WRITE", description = "write scope")
	}
		
		
		)))
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
					).externalDocs(new ExternalDocumentation()
							.description("AlgaWorks")
							.url("https://algaworks.com")				
							
					).tags(Arrays.asList(				
							new Tag().name("Cidades").description("Gerencia as cidades"),
							new Tag().name("Estados").description("Gerencia os estados"),
							new Tag().name("Cozinhas").description("Gerencia as cozinhas"),
							new Tag().name("Restaurantes").description("Gerencia os restaurantes"),
							new Tag().name("Usuarios").description("Gerencia os usuarios"),
							new Tag().name("Formas de Pagamento").description("Gerencia as formas de pagamento"),
							new Tag().name("Estatisticas").description("Gerencia as estatisticas"),
							new Tag().name("Grupos").description("Gerencia os Grupos"),
							new Tag().name("Permissoes").description("Gerencia as permissoes"),
							new Tag().name("Pedidos").description("Gerencia os pedidos"),
							new Tag().name("Produtos").description("Gerencia os produtos"),
							new Tag().name("Fluxo de Pedidos").description("Gerencia o fluxo de pedidos")						
					));
				
	}
	
	@Bean
	public OpenApiCustomiser openApiCustomiser() {
		return openApi -> {
			openApi.getPaths()
				.values()
				.stream()
				.flatMap(pathItem -> pathItem.readOperations().stream())
				.forEach(operation ->
				{
					ApiResponses responses = (ApiResponses) operation.getResponses();
					ApiResponse apiResponseNaoEncontrado = new ApiResponse().description("Recurso não encontrado");
					ApiResponse apiResponseErroInterno = new ApiResponse().description("Erro interno no servidor");
					ApiResponse apiResponseSemRepresentacao = new ApiResponse()
							.description("Recurso não possui representação que poderia ser aceita pelo consumidor");
					
					responses.addApiResponse("404", apiResponseNaoEncontrado);
					responses.addApiResponse("406", apiResponseSemRepresentacao);
					responses.addApiResponse("500", apiResponseErroInterno);
							
							
				}
						
				);
		};
	}
}
