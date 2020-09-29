package com.client.ui.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.client.ui.model.Product;
import com.client.ui.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	RestTemplate restTemplate = new RestTemplate();
	
	private  ArrayList<User> allUser = new ArrayList<User>();

	@Override
	public void save(User user) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:3003/api/users";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		user.setProductList(new ArrayList<>());
		user.setActive(1);
		user.setRole("USER");
		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
		//restTemplate.put(url, requestEntity);
		ResponseEntity<User> usr = restTemplate.exchange(url, HttpMethod.POST, requestEntity, User.class);
		System.out.println(usr.getBody());
	}
	
	
	@SuppressWarnings("unchecked")
	public User[] getAllUser() {
			ResponseEntity<User[]> listUser=restTemplate.getForEntity(
					"http://localhost:3003/api/users",
					User[].class);
			User[] users = listUser.getBody();
			return users;
			
			
	}

	@Override
	public User findByEmail(String email) {
		try {
			ResponseEntity<User[]> listUser=restTemplate.getForEntity(
					"http://localhost:3003/api/users",
					User[].class);
			List<User> allUser = Arrays.asList(listUser.getBody());
			for(User u : allUser) {
				if(u.getEmail().equals(email)) {
					return u;
				}
			}
			return null;
			
			}catch (Exception e) {
				return null;
			}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		List<Product> productlist1 = user.getProductList();
		List<Product> productlist = (findByEmail(user.getEmail())).getProductList();
		productlist1.addAll(productlist);
		user.setProductList(productlist1);
		put(user);
	}
	
	@Override
	public void put(User user) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:3003/api/users";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
		//restTemplate.put(url, requestEntity);
		ResponseEntity<User> usr = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);
		System.out.println(usr.getBody());
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
			ResponseEntity<User> listUser=restTemplate.getForEntity(
					"http://localhost:3003/api/users/"+id,
					User.class);
			User usr = listUser.getBody();
			return usr;
			
	}
	@Override
	public void updateUser(User user) {
		List<Product> productList = Collections.EMPTY_LIST;
		User userTemp = user;
		userTemp.setProductList(productList);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:3003/api/usersPut";
		User usr = restTemplate.postForObject(url, userTemp, User.class);
		System.out.println(usr);
	}
	
	public void updateList(User user) {
		User user1 = findByEmail(user.getEmail());
		user1.setProductList(user.getProductList());
		put(user);
	}

}
