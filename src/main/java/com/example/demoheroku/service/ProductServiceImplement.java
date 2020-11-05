package com.example.demoheroku.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demoheroku.controller.request.ProductRequest;
import com.example.demoheroku.exception.ProductNotFoundException;
import com.example.demoheroku.model.Product;
import com.example.demoheroku.repository.ProductRepository;

// annotation นี้เสมอ ถ้าให้ใช้ตัวนี้ไป injection หรือ bean factory
@Service
public class ProductServiceImplement implements ProductService {

	private StorageService storageService;
	private ProductRepository productRepository;

	// injection because @Service on StorageService
	// constructure
	ProductServiceImplement(StorageService storageService, ProductRepository productRepository) {
		this.storageService = storageService;
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProduct() {
//		 productRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
//		 เรียงแบบ DESC ใน SQL
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		// productRepository.findById(id); มีโอกาศ null ต้อง optional
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		} else {
			throw new ProductNotFoundException(id);
		}
	}

	@Override
	public Product createProduct(ProductRequest productRequest) {
		String fileName = storageService.store(productRequest.getImage());
		Product data = new Product();
		data.setName(productRequest.getName()).setImage(fileName).setPrice(productRequest.getPrice())
				.setStock(productRequest.getStock());
		return productRepository.save(data);
	}

	@Override
	public int updateProductStockByProductIDBorrow(ProductRequest productRequest, int id) {
		return productRepository.updateProductStockByIDBorrow(id, productRequest.getStock());
	}

	@Override
	public int updateProductStockByProductIDReturn(ProductRequest productRequest, int id) {
		return productRepository.updateProductStockByIDReturn(id, productRequest.getStock());
	}

	@Override
	public Product updateProduct(ProductRequest productRequest, Long id) {
		String fileName = storageService.store(productRequest.getImage());
		Optional<Product> product = productRepository.findById(id);
		// .isPresent() is have Value?
		if (product.isPresent()) {
			Product existProduct = product.get();
			if (fileName != null) {
				existProduct.setImage(fileName);
			}
			existProduct.setName(productRequest.getName()).setPrice(productRequest.getPrice())
					.setStock(productRequest.getStock());
			// save to DB with repository implement Jpa
			return productRepository.save(existProduct);

		} else {
			throw new ProductNotFoundException(id);
		}
	}

	@Override
	public void deleteProduct(Long id) {
		try {
			//
			productRepository.deleteById(id);

		} catch (Exception ex) {
			throw new ProductNotFoundException(id);
		}

	}

	@Override
	public Product getProductByName(String name) {
		// productRepository.findById(id); มีโอกาศ null ต้อง optional
		Optional<Product> product = productRepository.findTopByName(name);
		if (product.isPresent()) {
			return product.get();
		} else {
			throw new ProductNotFoundException(name);
		}
	}

	@Override
	public List<Product> getProductByNameAndStock(String name, int stock) {
		return productRepository.findByNameContainingAndStockGreaterThanOrderByStockDesc(name, stock);
	}

	@Override
	public List<Product> getProductOutOfStock() {
		return productRepository.checkOutOfStock();
	}

	@Override
	public List<Product> getProductByNameAndPrice(String name, int price) {
		return productRepository.searchNameAndPrice(name, price);
	}

}
