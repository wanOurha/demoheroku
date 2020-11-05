package com.example.demoheroku.service;

import java.util.List;

import com.example.demoheroku.controller.request.ProductRequest;
import com.example.demoheroku.model.Product;

// service for product
public interface ProductService {

	List<Product> getAllProduct();

	Product getProductById(Long id);

	Product getProductByName(String name);

	List<Product> getProductByNameAndStock(String name, int stock);

	Product createProduct(ProductRequest productRequest);

	Product updateProduct(ProductRequest productRequest, Long id);

	int updateProductStockByProductIDBorrow(ProductRequest productRequest, int productId);

	int updateProductStockByProductIDReturn(ProductRequest productRequest, int productId);

	void deleteProduct(Long id);

	List<Product> getProductOutOfStock();

	List<Product> getProductByNameAndPrice(String name, int price);
}
