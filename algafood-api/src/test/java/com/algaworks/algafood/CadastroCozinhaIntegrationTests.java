package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		//Cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Italiana");
		//Ação
		cadastroCozinha.salvar(novaCozinha);
		//Validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		
		Cozinha novaCozinha = new Cozinha();
		
		novaCozinha.setNome(null);
		
		cadastroCozinha.salvar(novaCozinha);
		
	}
	
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		
	}
	
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		
	}

}