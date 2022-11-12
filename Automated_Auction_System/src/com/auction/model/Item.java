package com.auction.model;

public class Item {
	private int item_id;
	private String item_name;
	private int price;
	private int quantity;
	private String category;
	private String date;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Item(int item_id, String item_name, int price, int quantity, String category) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}
	public Item(String item_name, int price, int quantity, String category) {
		super();
		this.item_name = item_name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}
	
	
	
	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name + ", price=" + price + ", quantity=" + quantity
				+ ", category=" + category + ", date=" + date + "]";
	}
	
	public Item() {
		
	}
}
