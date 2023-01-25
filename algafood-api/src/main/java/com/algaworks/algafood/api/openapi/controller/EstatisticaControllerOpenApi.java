package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.modelo.dto.VendaDiaria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estatisticas")
public interface EstatisticaControllerOpenApi {

	@ApiOperation(value = "Estatísticas", hidden = true)
	EstatisticasModel estatistica();
	
	@ApiOperation("Lista as vendas diarias")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dataCriacaoInicio", value = "data inicial da pesquisa", 
				example = "2022-12-01 00:00:00", dataType = "date-time"),
		@ApiImplicitParam(name = "dataCriacaoFim", value = "data final da pesquisa", 
			example = "2022-12-30 23:59:59", dataType = "date-time"),
		@ApiImplicitParam(name = "restauranteId", value = "ID do restaurante", 
			example = "1", dataType = "Long")
		
	})
	public List<VendaDiaria> consultarVendasDiarias(
			@ApiParam(value = "filtro", example = "periodo por data") VendaDiariaFilter filtro, 
			@ApiParam(value = "data e hora", example = "2022-12-01 00:00:00") String timeOffset);
	
	
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(
			@ApiParam(value = "filtro", example = "periodo por data") VendaDiariaFilter filtro, 
			@ApiParam(value = "data e hora", example = "2022-12-01 00:00:00") String timeOffset);
}
