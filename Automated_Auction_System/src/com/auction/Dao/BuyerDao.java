package com.auction.Dao;

import java.util.List;

import com.auction.exception.BuyerException;
import com.auction.exception.ItemException;
import com.auction.model.Buyer;
import com.auction.model.Item;

public interface BuyerDao {
	
	
	public String registerAsBuyer(Buyer buyer) throws BuyerException;
	
	public String loginAsBuyer(String email,String password) throws BuyerException;
	
	public List<Item> viewItemByCategory(String category) throws ItemException, BuyerException;
	
	public String buyItem(int item_id) throws ItemException;
	
}
