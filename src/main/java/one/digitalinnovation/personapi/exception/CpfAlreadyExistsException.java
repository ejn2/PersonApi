package one.digitalinnovation.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfAlreadyExistsException extends Exception {

	public CpfAlreadyExistsException(String msg) {
		super(msg);
	}
	
}
