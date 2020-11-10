package com.example.demoheroku.exception;

public class UnauthorizeException extends RuntimeException {

	public UnauthorizeException(String massage) {
		super(massage);
	}
}