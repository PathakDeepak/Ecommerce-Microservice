package com.ecommerce.products.productsmicroservice.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.products.productsmicroservice.model.Category;
import com.ecommerce.products.productsmicroservice.model.Product;
import com.ecommerce.products.productsmicroservice.services.CategoryService;
import com.ecommerce.products.productsmicroservice.services.ProductServices;

@RestController
@RequestMapping("/api")
public class ProductsRestController {

	@Autowired
	private ProductServices productsService;

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/allProducts")
	public ResponseEntity<List<Product>> findAll(){
		try {
			List<Product> products = productsService.finAllProducts();
			if (products != null) {
				return ResponseEntity.status(HttpStatus.OK).body(products);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET, value =
	 * "/categories/{categoryId}/products") public ResponseEntity<List<Product>>
	 * getAllProducts(@PathVariable Long categoryId) { try { List<Product> products
	 * = productsService.getAllProdcuts(categoryId); if (products != null) { return
	 * ResponseEntity.status(HttpStatus.OK).body(products); } else { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } } catch (Exception e)
	 * { return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); }
	 * 
	 * }
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
	public ResponseEntity<Product> getProducts(@PathVariable Long id) {
		try {
			Product product = productsService.getProduct(id);
			if (product != null) {
				return ResponseEntity.status(HttpStatus.OK).body(product);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	 @PostMapping("/products")
	public ResponseEntity<Product> addProducts(@RequestBody Product productdetails) {
		try {

			return null;

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

//	@RequestMapping(method = RequestMethod.PUT,value="/categories/{categoryId}/produtcs/{id}")
//	public ResponseEntity<String> updateProducts(@RequestBody Products products,@PathVariable String categoryId,@PathVariable Long id ) {
//		productsService.updateProduct(products);
//	}

	@DeleteMapping("/products/{id}")
	public String deleteProducts(@PathVariable Long id) {
		try {
			productsService.deleteProduct(id);
			return "Deleted";
		} catch (Exception e) {
			return "Error";
		}
	}
	
}
