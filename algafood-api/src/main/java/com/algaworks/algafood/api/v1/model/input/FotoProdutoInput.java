package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.core.validation.FileImage;
import com.algaworks.algafood.core.validation.FileSize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	@Schema(example = "Foto.jpg ou Foto.png")
	@NotNull
	@FileSize(max = "500KB")
	@FileImage(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;
	@Schema(example = "Carne agridoce")
	@NotBlank
	private String descricao;
}
