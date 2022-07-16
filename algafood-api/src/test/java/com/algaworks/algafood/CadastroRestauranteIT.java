package com.algaworks.algafood;

import java.util.Optional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.modelo.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;

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
	
	@Before
	private void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		
		dataBaseCleaner.clearTables();
		prepararDados();
	}
	
	
	
	private void prepararDados() {
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Teste");		
		Cozinha cozinha1 = cozinhaService.busarOuFalhar(1L);		
		restaurante1.setCozinha(cozinha1);
		
		restauranteRepository.save(restaurante1);
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Teste2");		
		Cozinha cozinha2 = cozinhaService.busarOuFalhar(2L);		
		restaurante1.setCozinha(cozinha2);
		
		restauranteRepository.save(restaurante2);
		
	}
	
	

}
