package com.ecommerce.users.usersmicroservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.users.usersmicroservice.model.User;
import com.ecommerce.users.usersmicroservice.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUser() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	public User getUser(Long id) {
			return userRepository.findById(id).get();
	}
	
	public User getUserByEmail(String email) {
		try {
			return userRepository.findByEmail(email);
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public User addUser(User user) {
		User usr =  userRepository.save(user);
		return usr;
	}

	public User updateUser(User user) {
		User usr = userRepository.save(user);
		return usr;
	}

	public void deleteUser(Long id) {
		try {
			userRepository.deleteById(id);

		} catch (Exception e) {

		}
	}

}
