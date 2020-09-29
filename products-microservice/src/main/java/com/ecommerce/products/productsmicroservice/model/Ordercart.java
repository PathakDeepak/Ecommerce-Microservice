package com.ecommerce.products.productsmicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ordercart {
	
	@Id
	 @Column(length = 64)
	private String orderID;
	
	private long userId;
	
	private Long amount;
	
	private String country;
	
	private String state;
	
	private int zip;
	
	private String address;
	
	public Ordercart() {
		// TODO Auto-generated constructor stub
	}
	

	public Ordercart(String orderID, long userId, Long amount, String country, String state, int zip, String address) {
		super();
		this.orderID = orderID;
		this.userId = userId;
		this.amount = amount;
		this.country = country;
		this.state = state;
		this.zip = zip;
		this.address = address;
	}




	public String getOrderID() {
		return orderID;
	}


	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}


	public long getUser() {
		return userId;
	}


	public void setUser(long userId) {
		this.userId = userId;
	}


	public Long getAmount() {
		return amount;
	}


	public void setAmount(Long amount) {
		this.amount = amount;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}
}
