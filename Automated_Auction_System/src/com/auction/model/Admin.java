package com.auction.model;

public class Admin {
	private int aid;
	private String admin_name;
	private String email;
	private String password;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
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
	public Admin(int aid, String admin_name, String email, String password) {
		super();
		this.aid = aid;
		this.admin_name = admin_name;
		this.email = email;
		this.password = password;
	}
	public Admin(String admin_name, String email, String password) {
		super();
		this.admin_name = admin_name;
		this.email = email;
		this.password = password;
	}
	
	public Admin() {
		
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", admin_name=" + admin_name + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
