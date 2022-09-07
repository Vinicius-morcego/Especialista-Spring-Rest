package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.modelo.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	@Autowired
	private DatabaseCleaner dataBaseCleaner;
	
	@LocalServerPort
	private int port;
	
	private static final Integer QUANTIDADE_DE_RESTAURANTES_CADASTRADOS = 2;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		
		dataBaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deve_retornar_2_restaurantes() {
		given()
		.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(QUANTIDADE_DE_RESTAURANTES_CADASTRADOS))
			.body("nome", hasItems("Teste", "Teste2"));
	}
	
	
	private void prepararDados() {
		
		
		Restaurante restaurante1 = new Restaurante();
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Indiana");
		cozinhaService.salvar(cozinha1);
		restaurante1.setNome("Teste");	
		restaurante1.setTaxaFrete(new BigDecimal(10));
		restaurante1.setCozinha(cozinhaService.busarOuFalhar(1L));		
		restauranteRepository.save(restaurante1);
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Teste2");		
		restaurante2.setTaxaFrete(new BigDecimal(15));
		restaurante2.setCozinha(cozinhaService.busarOuFalhar(1L));		
		restauranteRepository.save(restaurante2);
		
	}
	
	

}
