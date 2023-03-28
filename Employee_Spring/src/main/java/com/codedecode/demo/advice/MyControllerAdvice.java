package com.codedecode.demo.advice;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codedecode.demo.custom.exception.EmptyInputException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
	
	//custom exception
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput (EmptyInputException emptyInputException){
		
		return new ResponseEntity<String>("Input field is empty", HttpStatus.BAD_REQUEST);
	}
	
	//predefined default handler
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleEmptyInput (NoSuchElementException NoSuchElementException){
		
		return new ResponseEntity<String>("No value is present in the DataBase, Please change the request", HttpStatus.NOT_FOUND);
	}

	//override ResponseEntity
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object> ("Please change the http method type", HttpStatus.NOT_FOUND);
	}

}
