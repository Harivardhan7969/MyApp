package com.hari.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TodoAPIException.class)
   public	ResponseEntity<ErrorDetails> handleTodoException(
		   TodoAPIException todoAPIException,WebRequest webRequest){
	   
	   ErrorDetails errorDetails=new ErrorDetails(
			   LocalDateTime.now(),
			   todoAPIException.getMessage(),
			   webRequest.getDescription(false)
			   );
	   
	   return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
		
	}

}
