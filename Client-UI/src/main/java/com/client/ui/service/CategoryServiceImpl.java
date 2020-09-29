package com.client.ui.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.client.ui.model.Category;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	RestTemplate restTemplate = new RestTemplate();
	
	private  ArrayList<Category> allCategories = new ArrayList<Category>();
	
	@SuppressWarnings("unchecked")
	@Override
	public Category[] listCategory() {
			ResponseEntity<Category[]> listCategory=restTemplate.getForEntity(
					"http://localhost:3001/api/categories",
					Category[].class);
			Category[] categorylist = listCategory.getBody();
			return categorylist;
			
			
	}

}
