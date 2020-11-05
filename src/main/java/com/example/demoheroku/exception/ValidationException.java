package com.example.demoheroku.exception;

public class ValidationException extends RuntimeException {

	public ValidationException(String massage) {
		super(massage);
	}
}
