package com.example.demoheroku.exception;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(long id) {
		super("not found Product ID: " + id);
	}

	public ProductNotFoundException(String name) {
		super("not found Product Name: " + name);
	}

}
