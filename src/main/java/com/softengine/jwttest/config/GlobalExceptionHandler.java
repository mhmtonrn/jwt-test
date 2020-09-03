package com.softengine.jwttest.config;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Error> customExceptionHandler(AccessDeniedException ex, WebRequest request) {
        Error error = new Error(ex.getMessage(), 101);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception ex, WebRequest request) {
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), 102);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Error> validateException(ConstraintViolationException ex, WebRequest request) {
    	 Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

    	 Set<String> messages = new HashSet<>(constraintViolations.size());
    	 messages.addAll(constraintViolations.stream()
    	         .map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
    	                 constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
    	         .collect(Collectors.toList()));    	
    	
    	Error error = new Error(messages.toString(),103 );
    	logger.error(error.toString());
    	return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
    
    
    
    
    
}