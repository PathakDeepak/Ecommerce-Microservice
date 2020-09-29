package com.ecommerce.products.productsmicroservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.products.productsmicroservice.model.Category;
import com.ecommerce.products.productsmicroservice.model.Product;
import com.ecommerce.products.productsmicroservice.repository.CategoryRepository;
import com.ecommerce.products.productsmicroservice.repository.ProductRepository;

@Service
@Transactional
public class ProductServices {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Product> getAllProdcuts(Long categoryID){
		List<Product> productsList=new ArrayList<Product>();
		Category category= categoryRepository.findById(categoryID).get();
		category.getProductList().forEach(productsList::add);
		return productsList;
	}
	
	public List<Product> finAllProducts(){
		return productRepository.findAll();
	}
	
	public Product getProduct(@PathVariable Long id) {
		return productRepository.findById(id).get();
	}
	
	public Product addProduct(Product products) {
			return	productRepository.save(products);
	}
	
	public void updateProduct(Product products) {
		productRepository.save(products);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
