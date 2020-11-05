package com.example.demoheroku.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demoheroku.controller.request.ProductBorrowRequest;
import com.example.demoheroku.exception.ProductBorrowNotFoundException;
import com.example.demoheroku.exception.ProductNotFoundException;
import com.example.demoheroku.model.ProductBorrow;
import com.example.demoheroku.repository.ProductBorrowRepository;

@Service
public class ProductBorrowServiceImplement implements ProductBorrowService {

	private ProductBorrowRepository productBorrowRepository;

	ProductBorrowServiceImplement(ProductBorrowRepository productBorrowRepository) {
		this.productBorrowRepository = productBorrowRepository;

	}

	@Override
	public List<ProductBorrow> getAllBorrowInfo() {
		return productBorrowRepository.findAll();
	}

	@Override
	public ProductBorrow getBorrowInfoByBorrowID(Long borowId) {
		Optional<ProductBorrow> ProductBorrow = productBorrowRepository.findById(borowId);
		if (ProductBorrow.isPresent()) {
			return ProductBorrow.get();
		} else {
			throw new ProductBorrowNotFoundException(borowId);
		}
	}

	@Override
	public ProductBorrow createBorrowInfo(ProductBorrowRequest productBorrowRequest) {
		ProductBorrow data = new ProductBorrow();
		data.setUser_id(productBorrowRequest.getUser_id()).setBorrow_amount(productBorrowRequest.getBorrow_amount())
				.setIs_return(0).setProduct_id(productBorrowRequest.getProduct_id());
		return productBorrowRepository.save(data);
	}

	@Override
	public ProductBorrow updateBorrowInfo(ProductBorrowRequest borrowRequest, Long borrowId) {
		Optional<ProductBorrow> productBorrow = productBorrowRepository.findById(borrowId);
		// .isPresent() is have Value?
		if (productBorrow.isPresent()) {
			ProductBorrow existProductBorrow = productBorrow.get();
			existProductBorrow.setIs_return(borrowRequest.getIs_return());
			// save to DB with repository implement Jpa
			return productBorrowRepository.save(existProductBorrow);

		} else {
			throw new ProductNotFoundException(borrowId);
		}
	}

	@Override
	public List<ProductBorrow> getAllBorrowInfoByUserId(Long userId) {
		return productBorrowRepository.findAllBorrowByUserId(userId);
	}

	@Override
	public List<ProductBorrow> getAllBorrowInfoBorrowStatus(Long borrow_status) {
		return productBorrowRepository.findAllBorrowByBorrowStatus(borrow_status);
	}

	@Override
	public List<ProductBorrow> getBorrowInfoIsNotReturn() {
		return productBorrowRepository.findAllBorrowIsNotReturn();
	}
}
