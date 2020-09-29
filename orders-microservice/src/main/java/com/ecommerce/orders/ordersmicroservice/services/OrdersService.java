package com.ecommerce.orders.ordersmicroservice.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.orders.ordersmicroservice.model.Ordercart;
import com.ecommerce.orders.ordersmicroservice.repository.OrdersRepository;

@Service
public class OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	public List<Ordercart> getAllOrders()
	{
		try {
			return ordersRepository.findAll();
		}catch (Exception e) {
			System.out.println("SERVICE"+e.getMessage());

			return null;
		}
	
	}
	
	/*
	 * public List<Ordercart> getAllOrdersByUser(Long userId) { try { return
	 * ordersRepository.findByUserId(userId); }catch (Exception e) {
	 * System.out.println("SERVICE"+e.getMessage());
	 * 
	 * return null; } }
	 */
	
	public Ordercart getOrder(String id) {
		try {
			return ordersRepository.findById(id).get();
		
		}catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public Ordercart addOrder(Ordercart order) {
		Ordercart ordercart = ordersRepository.save(order);
		return ordercart;
	}
	
	public void orderOrder(Ordercart order) {
		ordersRepository.save(order);
	}
	
	public void deleteOrder(String id) {
		ordersRepository.deleteById(id);
	}
	
	
	
}
