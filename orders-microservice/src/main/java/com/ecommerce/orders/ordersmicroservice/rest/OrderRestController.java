package com.ecommerce.orders.ordersmicroservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.orders.ordersmicroservice.model.Ordercart;
import com.ecommerce.orders.ordersmicroservice.services.OrdersService;

@RestController
@RequestMapping("/api")
public class OrderRestController {

	@Autowired
	private OrdersService ordersService;
	
	@PostMapping("/orders")
	public Ordercart addOrder(@RequestBody Ordercart ordercart) {
		return ordersService.addOrder(ordercart);}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Ordercart>> getAllOrder() {
		try {
			List<Ordercart> orders=ordersService.getAllOrders();
			if (orders != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(orders);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				System.out.println("REST"+e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Ordercart> getAllOrderByUserId(@PathVariable(name = "orderId")String orderId) {
		try {
			Ordercart orders=ordersService.getOrder(orderId);
			if (orders != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(orders);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				System.out.println("REST"+e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
	}	
	
	
//	@RequestMapping(method = RequestMethod.GET,value="/categories/{id}")
//	public ResponseEntity<Category> getCategory(@PathVariable Long id) {
//		try {
//		Category category=categoryService.getCategory(id);
//		if (category != null) {
//            return ResponseEntity.status(HttpStatus.OK).body(category);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//		}catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}
//	}
//	
//	@RequestMapping(method = RequestMethod.POST,value="/categories")
//	public String addCategories(@RequestBody CategoryDetails categorydetail) {
//		try {
//				categoryService.addCategory(new Category(categorydetail.getName(), categorydetail.getDescription()));
//			    return "Success";
//			}catch (Exception e) {
//				return "Error";
//	    }
//	}
//	
//	@RequestMapping(method = RequestMethod.PUT,value="/categories")
//	public String updateCategories(@RequestBody Category category) {
//		try {
//			categoryService.updateCategory(category);
//		    return "Success";
//		}catch (Exception e) {
//			return "Error";
//    }
//	}
//	
//	@RequestMapping(method = RequestMethod.DELETE,value="/categories/{id}")
//	public String deleteaddCategories(@PathVariable Long id) {
//		try {
//			categoryService.deleteCategory(id);
//		    return "Success";
//		}catch (Exception e) {
//			return "Error";
//    }
//	}

	
}
