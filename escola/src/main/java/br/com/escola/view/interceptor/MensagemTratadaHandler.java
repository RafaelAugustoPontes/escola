package br.com.escola.view.interceptor;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.escola.controller.exception.UnauthorizedException;

@RestControllerAdvice
@Order(1)
public class MensagemTratadaHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void handleUnauthorizedException(UnauthorizedException ex) {
		throw ex;
	}

}
