package br.com.escola.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
class GlobalControllerExceptionHandler {

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(MalformedJwtException.class)
	public void handleConflict() {
	}

}