package com.example.demoheroku.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoheroku.controller.request.ProductRequest;
import com.example.demoheroku.exception.ValidationException;
import com.example.demoheroku.model.Product;
import com.example.demoheroku.service.ProductServiceImplement;

@org.springframework.stereotype.Controller
// @RestController ทำให้ request ส่งค่าคืน เป็น service เสมอ ทำให้ไม่ต้องใช้  @ResponseBody ที่ service
// @CrossOrigin("domain") อณุญาตืให้ access ได้ ไม่ติด CORS แบบ local หรือเฉพาะ controller นี้
// @CrossOrigin(methods = { RequestMethod.GET },origins = {"domain1","domain2"}) หมายถึงให้ GET อย่างเดียว และให้สิทธิเฉพาะ domain1 domain2
// @Slf4j lombok has log
@RestController
@RequestMapping("/product")

public class ProductController {

	private final ProductServiceImplement productServiceImplement;

	// injection because @Service on productServiceImplement
	ProductController(ProductServiceImplement productServiceImplement) {
		this.productServiceImplement = productServiceImplement;
	}

	// สามารถคุมระดับ method ได้
	@CrossOrigin
	@GetMapping()
	public List<Product> getAllProducts() {
		return productServiceImplement.getAllProduct();
	}

	// /say/1222 ใช้ {id} เป็น @PathVariable
	// productRepository.findById(id) ค้นหาที่ primary ที่ตรงกับ id
	@GetMapping("/{id}")
	public Product getProductByID(@PathVariable long id) {
		return productServiceImplement.getProductById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	// MultipartFile file เป็นค่า key,@Valid มาจาก annotation @NotEmpty ใน
	// ProductRequest,BindingResult ไว้ใช้ดักจับ result ของ service เป็น bean ที่
	// springframework.validation มีอยู่แล้ว
	// key ต้องใช้ ตามใน object ProductRequest productRequest
	// springboot has jacson lib to automate object to json
	// @RequestBody ใช้ กรณีจะให้ mapping model กับ json ได้
	public Product addProduct(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) {
		// Validation ตรวจจับข้อผิดพลาด
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
		}
		return productServiceImplement.createProduct(productRequest);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/{id}")
	// BindingResult อยู่ต่อจาก @Valid
	public Product editProducts(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult,
			@PathVariable long id) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField());
			});
		}
		return productServiceImplement.updateProduct(productRequest, id);
	}

	@PutMapping("/{productId}/set-amount-borrow")
	// BindingResult อยู่ต่อจาก @Valid
	public int updateStockByIdBorrow(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult,
			@PathVariable int productId) {
		return productServiceImplement.updateProductStockByProductIDBorrow(productRequest, productId);
	}

	@PutMapping("/{productId}/set-amount-return")
	// BindingResult อยู่ต่อจาก @Valid
	public int updateStockByIdReturn(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult,
			@PathVariable int productId) {
		return productServiceImplement.updateProductStockByProductIDReturn(productRequest, productId);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteProductsById(@PathVariable long id) {
		productServiceImplement.deleteProduct(id);
	}

	@GetMapping(path = "/search", params = "name")
	public Product serchProductsByname(@RequestParam String name) {
		return productServiceImplement.getProductByName(name);
	}

	@GetMapping(path = "/search", params = { "name", "stock" })
	public Product serchProductsBynameAndStock(@RequestParam String name, @RequestParam int stock) {
		return (Product) productServiceImplement.getProductByNameAndStock(name, stock);
	}

	@GetMapping("/outOfStock")
	public List<Product> checkOutOfStock() {
		return productServiceImplement.getProductOutOfStock();
	}

	@GetMapping(path = "/searchByNameAndPrice", params = { "name", "price" })
	public List<Product> searchByNameAndPrice(@RequestParam String name, @RequestParam int price) {
		return productServiceImplement.getProductByNameAndPrice(name, price);
	}
}
