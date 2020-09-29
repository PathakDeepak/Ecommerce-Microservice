package com.ecommerce.orders.ordersmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrdersMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersMicroserviceApplication.class, args);
	}

}
