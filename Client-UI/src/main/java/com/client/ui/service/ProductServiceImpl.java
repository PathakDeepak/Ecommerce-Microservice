package com.client.ui.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.client.ui.model.Category;
import com.client.ui.model.Product;



@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	
	RestTemplate restTemplate = new RestTemplate();
	
	private  List<Product> allProducts = new ArrayList<>();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> listProduct() {
		try {
			ResponseEntity<Product[]> listProduct=restTemplate.getForEntity(
					"http://localhost:3001/api/allProducts",
					Product[].class);
			return Arrays.asList(listProduct.getBody());
			}catch (Exception e) {
				return (List<Product>) ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Category findByCategory(String categoryId) {
		
		/*
		 * ResponseEntity<Product[]> listProduct=restTemplate.getForEntity(
		 * "http://localhost:3001/api/categories/"+categoryId, Product[].class);
		 * Product[] productlist = listProduct.getBody(); return productlist;
		 */
			ResponseEntity<Category> listProduct=restTemplate.getForEntity(
					"http://localhost:3001/api/categories/"+categoryId,
					Category.class);
			Category productlist = listProduct.getBody();
			return productlist;
			
	}

	@Override
	public ResponseEntity<Product> getProductById(String productId) {
		// TODO Auto-generated method stub
		try {
			ResponseEntity<Product> product = restTemplate.getForEntity("http://localhost:3001/api/products/"+productId,
					Product.class);
			return product;
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public ResponseEntity<String> addProduct(Product product) {
		// TODO Auto-generated method stub
		try {
			MultiValueMap<String, String> headers = new HttpHeaders();
			  headers.add("User-Agent", "Product add class");
			  headers.add("Accept-Language", "en-US");
			  
			  HttpEntity<Product> entity = new HttpEntity<>(product, headers);
			  restTemplate.postForObject("http://localhost:3003/api/products", entity, Product.class);
			  return ResponseEntity.status(HttpStatus.OK).body("Success");
			}
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		
	}

}
