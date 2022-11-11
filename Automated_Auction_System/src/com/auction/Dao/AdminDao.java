package com.auction.Dao;

import java.util.List;

import com.auction.exception.AdminException;
import com.auction.exception.BuyerException;
import com.auction.exception.SellerException;
import com.auction.model.Admin;
import com.auction.model.Buyer;
import com.auction.model.Seller;

public interface AdminDao {
	
	public String registerAdmin(Admin a) throws AdminException;
	
	public String loginAdmin(String username,String access_key) throws AdminException;
	
	public List<Buyer> viewBuyers() throws BuyerException;
	
	public List<Seller> viewSellers() throws SellerException;
	
	//public List<Item> viewSellingReport() throws ItemException;
}
