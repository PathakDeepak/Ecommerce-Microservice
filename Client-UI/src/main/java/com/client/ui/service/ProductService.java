package com.client.ui.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.client.ui.model.Category;
import com.client.ui.model.Product;



public interface ProductService {
	
	public ResponseEntity<String> addProduct(Product product);

	public List<Product> listProduct();

	public Category findByCategory(String categoryId);
	
	public ResponseEntity<Product> getProductById(String productId);

}
