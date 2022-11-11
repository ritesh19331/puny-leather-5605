package com.auction.model;

public class Buyer {
	
	private int bid;
	private String buyer_name;
	private String email;
	private String password;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Buyer(int bid, String buyer_name, String email, String password) {
		super();
		this.bid = bid;
		this.buyer_name = buyer_name;
		this.email = email;
		this.password = password;
	}
	
	
	
	public Buyer(int bid, String buyer_name, String email) {
		super();
		this.bid = bid;
		this.buyer_name = buyer_name;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Buyer [bid=" + bid + ", buyer_name=" + buyer_name + ", email=" + email + ", password=" + password + "]";
	}
	
	public Buyer() {
		
	}
}
