package com.ecommerce.products.productsmicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.products.productsmicroservice.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
