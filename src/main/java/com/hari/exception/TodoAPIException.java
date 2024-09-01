package com.hari.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TodoAPIException extends RuntimeException {
	
	private HttpStatus status;
	private String message;
	

}
