package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

public class ValorZeroIncluiDesricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object>{

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;
	
	@Override
	public void initialize(ValorZeroIncluiDescricao constraint) {

		this.valorField = constraint.valorField();
		this.descricaoField = constraint.descricaoField();
		this.descricaoObrigatoria = constraint.descricaoObrigatoria();
	}
	
	
	@Override
	public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
		var valido = true;
		
		try {
				var valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField)
					.getReadMethod().invoke(objetoValidacao);
			var descricao = (String)  BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField)
					.getReadMethod().invoke(objetoValidacao);
			if(valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
				
				valido = descricao.toLowerCase().contains(descricaoObrigatoria.toLowerCase());
			}
			
			return valido;
			
		} catch (Exception e) {

			throw new ValidationException(e);
		}
		
			
	}

}
