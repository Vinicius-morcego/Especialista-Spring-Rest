package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {


	@LocalServerPort
	private int port;
	
	
	
	@Autowired
	private DatabaseCleaner dataBaseCleaner;
	
	
	
	@Autowired 
	private CozinhaRepository cozinhaRepository;
	
	
	
	private static final Integer CONSULTAR_ID_COZINHA = 100;
	private static final Integer QUANTIDADE_DE_COZINHAS_CADASTRADAS = 2;
	

	private Cozinha cozinhaAmericana;
	private String jsonCorretoCozinhaChinesa; 
	
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		dataBaseCleaner.clearTables();
		prepararDados();
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json");
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given()			
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveConterTotalDeCozinhas_QuandoConsultarCozinhas() {
		given()			
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(QUANTIDADE_DE_COZINHAS_CADASTRADAS))
			.body("nome", hasItems("Indiana", "Americana"));
		
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given()
			.body(jsonCorretoCozinhaChinesa)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}
	
	//GET para consultar cozinha pelo id - {cozinhaId}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhaExistente() {
		given()			
		.accept(ContentType.JSON)
	.when()
		.get("/{cozinhaId}", cozinhaAmericana.getId())
	.then()
		.statusCode(HttpStatus.OK.value())
		.body("nome", equalTo(cozinhaAmericana.getNome()));
		
	
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		given()			
		.accept(ContentType.JSON)
	.when()
		.get("/{cozinhaId}", CONSULTAR_ID_COZINHA)
	.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
		
	
	}
	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Indiana");
		cozinhaRepository.save(cozinha1);
		
		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);

		//quantidadeDeCozinhas = cozinhaRepository.count();
		
	}

}