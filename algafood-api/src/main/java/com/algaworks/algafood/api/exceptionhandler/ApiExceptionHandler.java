package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	public static final String MSG_ERRO_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o "
			+ "erro peristir, entre em contato com o administrador do sistema";
		
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		

		if(rootCause instanceof InvalidFormatException){
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}else if(rootCause instanceof UnrecognizedPropertyException) {
		    return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);
		}else if(rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request);
		}
		
			ProblemType problemType = ProblemType.ERRO_DE_DIGITACAO;
			String detail = "Requisi????o inv??lida. Provavel erro de digita????o.";
			
			
			Problem problem = createProblemBuilder(status, problemType, detail)
					.userMessage(detail)
					.build();
			return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);		
	}	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleValidationInternal(ex, status, ex.getBindingResult(), headers, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
		
		
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = "O recurso "+ex.getRequestURL()+", que voc?? tentou acessar, ?? inexistente";
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String detail = String.format("O par??metro de URL '%s' recebeu o valor de '%s', que ?? de um tipo inv??lido. "
				+ "Corrija e informe um valor compat??vel com o tipo '%s'.", ex.getName(),
				ex.getValue(), ex.getRequiredType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_USUARIO_FINAL)
				.build();		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			String path = joinPath(ex.getPath());
		    ProblemType problemType = ProblemType.PROPRIEDADE_IGNORADA;
	    
			String detail = String.format("Requisi????o inv??lida, propriedade '%s' ignorada na representa????o!", path);
		    Problem problem = createProblemBuilder(status, problemType, detail)
		    		.userMessage(MSG_ERRO_USUARIO_FINAL)
		    		.build();
		    return handleExceptionInternal(ex, problem, headers, status, request);
	}


	private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

			String path = joinPath(ex.getPath());
		    ProblemType problemType = ProblemType.PROPRIEDADE_INEXISTENTE;
		    String detail = String.format("Requisi????o inv??lida, propriedade '%s' inexistente! remova e tente novamente", path);		    
		    
		    Problem problem = createProblemBuilder(status, problemType, detail)
		    		.userMessage(detail)
		    		.build();
		    return handleExceptionInternal(ex, problem, headers, status, request);
	}


	private String joinPath(List<Reference> references) {
		
		return references.stream().map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
	}


	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.ERRO_DE_DIGITACAO;
		String detail = String.format("A propriedade '%s' recebeu um valor do tipo '%s'. Corrija com um tipo compat??vel a %s.",
				path);
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleExceptionGenerica(Exception ex, WebRequest request){
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		
		String detail = MSG_ERRO_USUARIO_FINAL;
		ex.printStackTrace();
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);	
	}
	
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<?> handleValidacaoException(ValidacaoException ex, WebRequest request){
		
		return handleValidationInternal(ex, HttpStatus.BAD_REQUEST, ex.getBindingResult(), new HttpHeaders(), request);
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpStatus status, BindingResult bindingResult,
			HttpHeaders headers, WebRequest request) {
		
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = "Um ou mais dados est??o inv??lidos. Fa??a o preenchimento correto e tente novamente.";
		List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
				.map(objectErrors -> {
					String message = messageSource.getMessage(objectErrors, LocaleContextHolder.getLocale());
					
					String name = objectErrors.getObjectName();
					
					if(objectErrors instanceof FieldError) {
						name = ((FieldError) objectErrors).getField();
					}
					return Problem.Object.builder()
						.name(name)
						.userMessage(message)
						.build();
				})
				.collect(Collectors.toList());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.objects(problemObjects)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
			
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);		
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		 
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_DE_NEGOCIO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request); 
	}
	

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request){
		if(body == null) {
			body = Problem.builder().timestamp(LocalDateTime.now()).status(status.value()).title(status.getReasonPhrase()).userMessage(MSG_ERRO_USUARIO_FINAL).build();
		}else if(body instanceof String) {
			body = Problem.builder().timestamp(LocalDateTime.now()).status(status.value()).title((String) body).userMessage(MSG_ERRO_USUARIO_FINAL).build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
				.status(status.value())
				.timestamp(LocalDateTime.now())
				.type(problemType.getUri())
				.title(problemType.getTitulo())
				.detail(detail);
				
				
	};
}

