package one.digitalinnovation.personapi.handler;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import one.digitalinnovation.personapi.exception.DefaultMessageError;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ PersonNotFoundException.class })
	public ResponseEntity<?> handlerPersonNotFoundException(PersonNotFoundException ex) {


		return new ResponseEntity<>(new DefaultMessageError(
				"/api/v1/people/",
				
				ex.getMessage(),
				
				HttpStatus.NOT_FOUND.value(),
				
				LocalDateTime.now()
				),
				
				HttpStatus.NOT_FOUND);
	}
		

}
