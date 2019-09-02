package br.com.escola.controller.exception;

public class RegraDeNegocioException extends RuntimeException {

	private static final long serialVersionUID = 4824651731467562249L;

	public RegraDeNegocioException(String message) {
		super(message);
	}

}
