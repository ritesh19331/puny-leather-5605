package com.auction.Dao;

import java.util.List;
import java.util.Map;

import com.auction.exception.BuyerException;
import com.auction.exception.ItemException;
import com.auction.model.Buyer;
import com.auction.model.Item;
import com.auction.model.ItemRequest;

public interface BuyerDao {
	
	
	public String registerAsBuyer(Buyer buyer) throws BuyerException;
	
	public String loginAsBuyer(String email,String password) throws BuyerException;
	
	public List<Item> viewItemByCategory(String category) throws ItemException;
	
	public List<Item> viewAllItem() throws ItemException;
	
	public String buyItem(int item_id,int offer_price) throws ItemException;
	
	public List<ItemRequest> viewOtherBuyersForBidItem() throws ItemException,BuyerException;
}
