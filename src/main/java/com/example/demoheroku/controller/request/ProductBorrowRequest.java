package com.example.demoheroku.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductBorrowRequest {

//	@NotEmpty(message = "is Empty")
//	@Size(min = 2, max = 100)
	private long user_id;
	private long product_id;
	private long borrow_amount;
	private long is_return;

}
