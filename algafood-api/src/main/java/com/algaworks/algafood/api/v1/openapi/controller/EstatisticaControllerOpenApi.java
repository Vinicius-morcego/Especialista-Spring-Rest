package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.modelo.dto.VendaDiaria;


public interface EstatisticaControllerOpenApi {
	
	EstatisticasModel estatistica();
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);
}
