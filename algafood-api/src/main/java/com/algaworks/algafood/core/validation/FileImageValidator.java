package com.algaworks.algafood.core.validation;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class FileImageValidator implements ConstraintValidator<FileImage, MultipartFile>{

	private List<String> alloweds;
	
	@Override
	public void initialize(FileImage constraintAnnotation) {
		
		this.alloweds = Arrays.asList(constraintAnnotation.allowed());
	}
	
	@Override
	public boolean isValid(MultipartFile multiPartFile, ConstraintValidatorContext context) {
		
		return multiPartFile == null || alloweds.contains(multiPartFile.getContentType()) ;
	}

}
