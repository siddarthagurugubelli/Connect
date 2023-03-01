package socialMediaApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FollowException extends Exception{

	private static final long serialVersionUID = 1L;

	public FollowException(String message) {
		super(message);
	}
}
