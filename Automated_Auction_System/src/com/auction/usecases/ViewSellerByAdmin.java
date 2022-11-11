package com.auction.usecases;

import java.util.List;

import com.auction.Dao.AdminDao;
import com.auction.Dao.AdminDaoImpl;
import com.auction.exception.SellerException;
import com.auction.model.Seller;

public class ViewSellerByAdmin {
	
	public static void main(String[] args) {
		
		
		
		AdminDao dao =new AdminDaoImpl();
		try {
			List<Seller> ls =	dao.viewSellers();
			
			ls.forEach((seller) -> System.out.println(seller));
			
		} catch (SellerException e) {
			System.out.println(e.getMessage());
		}
		
	}
}