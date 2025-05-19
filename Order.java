package com.mstore.order;

public class Order {
	private int OrderId;
	private String User;
	private String Song;
	private String Date;
	private Double Price;
	
	public Order(String OrderId, String user, String song, String date, Double price) {
		User = user;
		Song = song;
		Date = date;
		Price = price;
		
		try {
			this.OrderId = Integer.parseInt(OrderId);
		} catch (Exception e) {
			this.OrderId = 1;
		}
	}
	
	public Order(String user, String song, String date, Double price) {
		User = user;
		Song = song;
		Date = date;
		Price = price;
	}
	
	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getSong() {
		return Song;
	}

	public void setSong(String song) {
		Song = song;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}
	
	
}	
