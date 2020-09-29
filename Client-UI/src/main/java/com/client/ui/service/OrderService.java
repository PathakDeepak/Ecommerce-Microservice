package com.client.ui.service;

import java.util.Optional;

import com.client.ui.model.Ordercart;

public interface OrderService {

	void save(Ordercart orderSave);

	Optional<Ordercart> findById(String orderId);

}
