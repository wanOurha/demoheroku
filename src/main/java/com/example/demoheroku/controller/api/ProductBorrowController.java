package com.example.demoheroku.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoheroku.controller.request.ProductBorrowRequest;
import com.example.demoheroku.exception.ValidationException;
import com.example.demoheroku.model.ProductBorrow;
import com.example.demoheroku.service.ProductBorrowServiceImplement;

@RestController
@RequestMapping("/borrow")
public class ProductBorrowController {

	private final ProductBorrowServiceImplement productBorrowServiceImplement;

	ProductBorrowController(ProductBorrowServiceImplement productBorrowServiceImplement) {
		this.productBorrowServiceImplement = productBorrowServiceImplement;
	}

	@GetMapping("/getAllBorrow")
	public List<ProductBorrow> getAllBorrow() {
		return productBorrowServiceImplement.getAllBorrowInfo();
	}

	@GetMapping("/{id}")
	public ProductBorrow getBorrowByBorrowId(@PathVariable Long id) {
		return productBorrowServiceImplement.getBorrowInfoByBorrowID(id);
	}

	@GetMapping("/user/{userid}")
	public List<ProductBorrow> getBorrowByUserId(@PathVariable Long userid) {
		return productBorrowServiceImplement.getAllBorrowInfoByUserId(userid);
	}

	@GetMapping("/borrow-status/{isReturn}")
	public List<ProductBorrow> getBorrowByBorrowStatus(@PathVariable Long isReturn) {
		return productBorrowServiceImplement.getAllBorrowInfoBorrowStatus(isReturn);
	}

	@GetMapping("/borrow-not-status")
	public List<ProductBorrow> getBorrowByNotBorrowStatus() {
		return productBorrowServiceImplement.getBorrowInfoIsNotReturn();
	}

	@PostMapping("/postborrow")
	public ProductBorrow postBorrow(@Valid @RequestBody ProductBorrowRequest productBorrowRequest,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
		}
		return productBorrowServiceImplement.createBorrowInfo(productBorrowRequest);
	}

	@PutMapping("/{id}")
	// BindingResult อยู่ต่อจาก @Valid
	public ProductBorrow updateBorrowInfo(@Valid @RequestBody ProductBorrowRequest productBorrowRequest,
			BindingResult bindingResult, @PathVariable long id) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
		}
		return productBorrowServiceImplement.updateBorrowInfo(productBorrowRequest, id);
	}

}
