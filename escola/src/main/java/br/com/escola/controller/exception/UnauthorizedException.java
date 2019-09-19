package br.com.escola.controller.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2718092823750483612L;

	public UnauthorizedException(String msg) {
		super(msg);
	}

}
