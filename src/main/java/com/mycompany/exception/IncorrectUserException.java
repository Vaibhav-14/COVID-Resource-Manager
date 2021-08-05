package com.mycompany.exception;

public class IncorrectUserException extends RuntimeException {

	public IncorrectUserException() {
	}

	public IncorrectUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IncorrectUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectUserException(String message) {
		super(message);
	}

	public IncorrectUserException(Throwable cause) {
		super(cause);
	}

}
