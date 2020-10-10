package br.com.tokiomarine.exception;

public class ViagemException extends Exception {

	private static final long serialVersionUID = 7953509525725145387L;

	public ViagemException() {
		super();
	}

	public ViagemException(String message, Throwable cause) {
		super(message, cause);
	}

	public ViagemException(String message) {
		super(message);
	}

	public ViagemException(Throwable cause) {
		super(cause);
	}

}
