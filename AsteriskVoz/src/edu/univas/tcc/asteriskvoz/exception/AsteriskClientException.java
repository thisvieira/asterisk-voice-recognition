package edu.univas.tcc.asteriskvoz.exception;

public class AsteriskClientException extends Throwable {

	private static final long serialVersionUID = 21479553891059080L;

	public AsteriskClientException() {
		super();
	}
	
	public AsteriskClientException(String message) {
		super(message);
	}
	
	public AsteriskClientException(Exception innerException) {
		super(innerException);
	}
	
	public AsteriskClientException(String message, Exception innerException) {
		super(message, innerException);
	}
}
