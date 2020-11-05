package com.example.demoheroku.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// GlobalException ประยุกต์จากการสร้าง bean
// use create exception and manage exception 
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String handlerProductNotfound(ProductNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String handlerStorageException(StorageException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerValidationException(ValidationException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handlerUserDuplicateException(UserDuplicateException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	String handlerWrongUserOrPasswordException(WrongUserOrPasswordException ex) {
		return ex.getMessage();
	}

}
