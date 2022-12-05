package com.algaworks.algafood.core.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;



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
