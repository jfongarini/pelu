package com.figaro.exception;

import org.springframework.http.HttpStatus;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  
@RestController  
public class GlobalExceptionHandler {  
  
    private static final String MSG_DUPLICADO = "Ya existe un elemento con estos datos";

	@ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ApiError handleException(Exception e){
    	return new ApiError(HttpStatus.BAD_REQUEST, MSG_DUPLICADO);
    }  
  
  
}  