package com.example.demoheroku.exception;

public class StorageException extends RuntimeException {

	public StorageException(String massage) {
		super(massage);
	}
}
