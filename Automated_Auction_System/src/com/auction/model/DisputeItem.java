package com.auction.model;

public class DisputeItem {
	
	private String item_name;
	private int item_id;
	private int seller_id;
	private int max_price;
	private int buyer_id;
	private int totalBuyRequest;
	
	
	
	public DisputeItem(String item_name, int item_id, int seller_id, int max_price, int buyer_id, int totalBuyRequest) {
		super();
		this.item_name = item_name;
		this.item_id = item_id;
		this.seller_id = seller_id;
		this.max_price = max_price;
		this.buyer_id = buyer_id;
		this.totalBuyRequest = totalBuyRequest;
	}
	public int getTotalBuyRequest() {
		return totalBuyRequest;
	}
	public void setTotalBuyRequest(int totalBuyRequest) {
		this.totalBuyRequest = totalBuyRequest;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public int getMax_price() {
		return max_price;
	}
	public void setMax_price(int max_price) {
		this.max_price = max_price;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	@Override
	public String toString() {
		return "DisputeItem [item_name=" + item_name + ", item_id=" + item_id + ", seller_id=" + seller_id
				+ ", max_price=" + max_price + ", buyer_id=" + buyer_id + ", totalBuyRequest=" + totalBuyRequest + "]";
	}
	
	
	
	
	
}
