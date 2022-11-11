package com.auction.Dao;

import java.util.List;

import com.auction.exception.ItemException;
import com.auction.exception.SellerException;
import com.auction.model.Item;
import com.auction.model.Seller;
import com.auction.model.SoldItem;

public interface SellerDao {
	public String registerAsSeller(Seller seller) throws SellerException;
	
	public String loginAsSeller(String email,String password) throws SellerException;
	
	public String addNewItem(Item item) throws ItemException;
	
	public String updateItem(int item_id,int new_price,int new_quantity) throws ItemException;
	
	public String removeItem(int item_id) throws ItemException;
	
	public List<SoldItem> soldItemList() throws ItemException;
}
