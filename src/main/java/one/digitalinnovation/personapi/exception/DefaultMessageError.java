package one.digitalinnovation.personapi.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DefaultMessageError {

	private static final long serialVersionUID = 1L;
	
	private String pathName;
	private String message;
	private int statusCode;
	private LocalDateTime timestamps;
	
	public DefaultMessageError(String pathName, String message, int statusCode, LocalDateTime timestamps) {
		this.pathName = pathName;
		this.message = message;
		this.statusCode = statusCode;
		this.timestamps = timestamps;
	}
	
	
	
}
