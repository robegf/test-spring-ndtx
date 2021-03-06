package id.robegf.inditex.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -206695902115041739L;

	public PriceNotFoundException() {
		super();
	}
}
