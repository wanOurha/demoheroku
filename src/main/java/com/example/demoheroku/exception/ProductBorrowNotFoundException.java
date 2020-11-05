package com.example.demoheroku.exception;

public class ProductBorrowNotFoundException extends RuntimeException {

	public ProductBorrowNotFoundException(long borrowId) {
		super("not found Borrow ID: " + borrowId);
	}

}
