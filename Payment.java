package com.mstore.payment;

public class Payment {
	private String PaymentId;
    private String User;
    private String Orders;
    private String Date;
    private Double TotalPrice;
    private String status;
    private String paymentMethod;
    
    public Payment(String paymentId, String user, String orders, String date, Double totalPrice, String status, String paymentMethod) {
    	this.PaymentId = paymentId;
    	this.User = user;
    	this.Orders = orders;
    	this.Date = date;
    	this.TotalPrice = totalPrice;   	
    	this.status = status;
    }
    
    public Payment(String paymentId, String user, String orders, String date, String totalPrice, String status, String paymentMethod) {
    	this.PaymentId = paymentId;
    	this.User = user;
    	this.Orders = orders;
    	this.Date = date;
    	this.status = status;
    	try {
			this.TotalPrice = Double.parseDouble(totalPrice);
		} catch (NumberFormatException e) {
			this.TotalPrice = 0.0;
    	}	
    }
    
	public String getPaymentId() {
		return PaymentId;
	}

	public void setPaymentId(String paymentId) {
		PaymentId = paymentId;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getOrders() {
		return Orders;
	}

	public void setOrders(String orders) {
		Orders = orders;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		TotalPrice = totalPrice;
	}

    public void setStatus(String status) {
    	this.status = status;
    }
    
	public String getStatus() {
		return status;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
