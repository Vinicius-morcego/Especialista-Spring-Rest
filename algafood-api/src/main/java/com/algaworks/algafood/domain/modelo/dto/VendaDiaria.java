package com.algaworks.algafood.domain.modelo.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VendaDiaria {
	
	private Date data;
	private Long totalVendas;
	private BigDecimal totalFaturado;
	public VendaDiaria(java.sql.Date data, Long totalVendas, BigDecimal totalFaturado) {
		
		this.data = new Date(data.getTime()) ;
		this.totalVendas = totalVendas;
		this.totalFaturado = totalFaturado;
	}
	
	

}
