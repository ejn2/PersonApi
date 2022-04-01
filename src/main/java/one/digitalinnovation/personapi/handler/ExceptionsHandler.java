package one.digitalinnovation.personapi.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import one.digitalinnovation.personapi.exception.CpfAlreadyExistsException;
import one.digitalinnovation.personapi.exception.DefaultMessageError;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	
	// =================================== [ Handler Person Not Found ] ===================================
	
	@ExceptionHandler({ PersonNotFoundException.class })
	public ResponseEntity<?> handlerPersonNotFoundException(PersonNotFoundException ex) {


		return new ResponseEntity<>(
				new DefaultMessageError(ex.getMessage(), HttpStatus.NOT_FOUND.value()),
				HttpStatus.NOT_FOUND);
	}

	
	
	// =================================== [ Handler Cpf already exists ] ===================================
	
	@ExceptionHandler({ CpfAlreadyExistsException.class })
	public ResponseEntity<?> handlerCpfAlreadyExistsException(CpfAlreadyExistsException ex) {

		return new ResponseEntity<>(
				new DefaultMessageError(ex.getMessage(),HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST);
	}
	
	
	// =================================== [ Handler Json Error ] ===================================
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String customMessage = "Json error";
		
		return new ResponseEntity<>(
				new DefaultMessageError(customMessage, status.value()),
				status
				);
	}

	
	// =================================== [ Handler Validations errors ] ===================================
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(err -> 
			errors.put(err.getField(), err.getDefaultMessage())
				);
		
		return new ResponseEntity<>(
				new DefaultMessageError(errors, status.value()),
				status);
	}

	// =================================== [ Handler Method Not Allowed ] ===================================
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		
		
		return new ResponseEntity<>(
				new DefaultMessageError(ex.getMessage(), status.value()),
				status
				);

	}

}
