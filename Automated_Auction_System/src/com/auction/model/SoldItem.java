package com.auction.model;

public class SoldItem {
	
	private int item_id;
	private String item_name;
	private int trade_price;
	private int buyer_id;
	private int seller_id;
	
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
	public int getTrade_price() {
		return trade_price;
	}
	public void setTrade_price(int trade_price) {
		this.trade_price = trade_price;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public SoldItem(int item_id, String item_name, int trade_price, int buyer_id, int seller_id) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.trade_price = trade_price;
		this.buyer_id = buyer_id;
		this.seller_id = seller_id;
	}
	@Override
	public String toString() {
		return "SoldItem [item_id=" + item_id + ", item_name=" + item_name + ", trade_price=" + trade_price
				+ ", buyer_id=" + buyer_id + ", seller_id=" + seller_id + "]";
	}
	
	
}
