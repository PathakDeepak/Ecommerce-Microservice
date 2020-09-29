package com.ecommerce.products.productsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductsMicroserviceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductsMicroserviceApplication.class, args);
	}

}
