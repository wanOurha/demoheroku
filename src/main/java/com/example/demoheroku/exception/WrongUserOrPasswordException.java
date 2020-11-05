package com.example.demoheroku.exception;

import java.io.IOException;

public class WrongUserOrPasswordException extends RuntimeException {

	public WrongUserOrPasswordException(IOException ex) {
		super(ex.getMessage());
	}

}
