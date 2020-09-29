package com.client.ui.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.client.ui.model.Ordercart;
import com.client.ui.model.User;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Override
	public void save(Ordercart orderSave) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:3002/api/orders";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Ordercart> requestEntity = new HttpEntity<>(orderSave, headers);
		//restTemplate.put(url, requestEntity);
		ResponseEntity<Ordercart> order = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Ordercart.class);
		System.out.println(order.getBody());

	}

	@Override
	public Optional<Ordercart> findById(String orderId) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ordercart> listOrder=restTemplate.getForEntity(
				"http://localhost:3002/api/orders/"+orderId,
				Ordercart.class);
		Ordercart order = listOrder.getBody();
		return Optional.of(order);
	}

}
