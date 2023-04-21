package com.algaworks.algafood.domain.modelo.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VendaDiaria {
	
	private Date dataCriacao;
	private Long totalVendas;
	private BigDecimal totalFaturado;
	public VendaDiaria(java.sql.Date dataCriacao, Long totalVendas, BigDecimal totalFaturado) {
		
		this.dataCriacao = new Date(dataCriacao.getTime()) ;
		this.totalVendas = totalVendas;
		this.totalFaturado = totalFaturado;
	}
	
	

}
