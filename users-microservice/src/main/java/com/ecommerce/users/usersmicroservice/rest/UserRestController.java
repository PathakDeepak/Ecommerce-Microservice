package com.ecommerce.users.usersmicroservice.rest;

import java.util.List;
import java.util.Optional;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.users.usersmicroservice.model.User;
import com.ecommerce.users.usersmicroservice.service.UserServices;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	UserServices userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser() {
		try {
			List<User> users=userService.getAllUser();
			if (users != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(users);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		try {
		User user=userService.getUser(id);
		if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
			return userService.updateUser(user);
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);;
		    return "Success";
		}catch (Exception e) {
			return "Error";
    }
	}
	
	/*
	 * @GetMapping("/user/{id}") public User getUserById(@PathVariable String id) {
	 * Long idTemp = Long.parseLong(id); return userService.getUser(idTemp); }
	 */

	
}
