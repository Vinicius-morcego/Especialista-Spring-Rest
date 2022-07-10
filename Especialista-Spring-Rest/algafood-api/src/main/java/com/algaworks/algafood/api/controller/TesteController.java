package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.modelo.Cozinha;
import com.algaworks.algafood.domain.modelo.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping(value = "/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> buscarCozinhas(String nome){
		return cozinhaRepository.findTodasCozinhasByNome(nome);
	}
	
	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> buscarCozinha(String nome){
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/restaurante/primeiro-restaurante-nome")
	public Optional<Restaurante> buscarPrimeiroRestaurantePorNome(String nome){
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}

	@GetMapping("/restaurante/top2-por-nome")
	public List<Restaurante> listarTop2PorNome(String nome){
		return restauranteRepository.findTop2RestaurantesByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/exists-nome")
	public boolean existsPorNome(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public int contarPorCozinha(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> consultarPorNome(String nome, Long cozinhaId){
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/por-nome-frete")
	public List<Restaurante> consultarPorNomeFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepository.consultar(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> consultarComFreteGratis(String nome){
				
		return restauranteRepository.findComFreteGratis(nome);
		
	}
	
	@GetMapping("/restaurante/primeiro")
	public Optional<Restaurante> consultarPrimeiro(){
				
		return restauranteRepository.buscarPrimeiro();
		
	}
	
	@GetMapping("/cozinha/primeira")
	public Optional<Cozinha> consultarPrimeira(){
				
		return cozinhaRepository.buscarPrimeiro();
		
	}
	
}
