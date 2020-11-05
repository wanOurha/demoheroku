package com.example.demoheroku.exception;

public class UserDuplicateException extends RuntimeException {

	public UserDuplicateException(String username) {
		super(username + " :already exists.");
	}

}
