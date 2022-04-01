package one.digitalinnovation.personapi.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DefaultMessageError {

	private static final long serialVersionUID = 1L;
	
	private final String PATHNAME = "/api/v1/people/";
	private final LocalDateTime TIMESTAMPS = LocalDateTime.now();
	private String message;
	private Map<String, String> fieldError = new HashMap<>();
	private int statusCode;
	
	public DefaultMessageError(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}
	
	public DefaultMessageError(Map<String, String> errors, int statusCode) {
		this.message = "Field error";
		this.fieldError = errors;
		this.statusCode = statusCode;
	}
		
}
