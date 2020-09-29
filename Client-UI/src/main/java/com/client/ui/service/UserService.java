package com.client.ui.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.client.ui.model.User;

public interface UserService {
	
	public User findByEmail(String email);
	
	public User[] getAllUser();
	public void save(User user);
	
	public void update(User user);
	
	public void put(User user);
	
	public User getUser(Long id);
	
	public void updateUser(User user);

	public void updateList(User user);
	
}
