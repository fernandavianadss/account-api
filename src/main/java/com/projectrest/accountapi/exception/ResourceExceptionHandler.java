package com.projectrest.accountapi.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequest(BadRequestException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;		
		StandardError err = new StandardError(e.getMessage());
		
		return ResponseEntity.status(status).body(err);
	}
}
