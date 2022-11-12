package com.auction.model;

public class ItemRequest {
	private int seller_id;
	private int buyer_id;
	private String item_name;
	private int item_id;
	private String category;
	private int your_price_offered;
	private int best_price_offered;
	private String auction_end_time;
	public ItemRequest(int seller_id, int buyer_id, String item_name, int item_id, String category,
			int your_price_offered, int best_price_offered, String auction_end_time) {
		super();
		this.seller_id = seller_id;
		this.buyer_id = buyer_id;
		this.item_name = item_name;
		this.item_id = item_id;
		this.category = category;
		this.your_price_offered = your_price_offered;
		this.best_price_offered = best_price_offered;
		this.auction_end_time = auction_end_time;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getYour_price_offered() {
		return your_price_offered;
	}
	public void setYour_price_offered(int your_price_offered) {
		this.your_price_offered = your_price_offered;
	}
	public int getBest_price_offered() {
		return best_price_offered;
	}
	public void setBest_price_offered(int best_price_offered) {
		this.best_price_offered = best_price_offered;
	}
	public String getAuction_end_time() {
		return auction_end_time;
	}
	public void setAuction_end_time(String auction_end_time) {
		this.auction_end_time = auction_end_time;
	}
	@Override
	public String toString() {
		return "ItemRequest [seller_id=" + seller_id + ", buyer_id=" + buyer_id + ", item_name=" + item_name
				+ ", item_id=" + item_id + ", category=" + category + ", your_price_offered=" + your_price_offered
				+ ", best_price_offered=" + best_price_offered + ", auction_end_time=" + auction_end_time + "]";
	}
	
	
}
