package com.example.demoheroku.service;

import java.util.List;

import com.example.demoheroku.controller.request.ProductBorrowRequest;
import com.example.demoheroku.model.ProductBorrow;

public interface ProductBorrowService {
	List<ProductBorrow> getAllBorrowInfo();

	List<ProductBorrow> getBorrowInfoIsNotReturn();

	List<ProductBorrow> getAllBorrowInfoByUserId(Long userId);

	List<ProductBorrow> getAllBorrowInfoBorrowStatus(Long borrow_status);

	ProductBorrow getBorrowInfoByBorrowID(Long borowId);

	ProductBorrow createBorrowInfo(ProductBorrowRequest borrowRequest);

	ProductBorrow updateBorrowInfo(ProductBorrowRequest borrowRequest, Long borowId);

}
