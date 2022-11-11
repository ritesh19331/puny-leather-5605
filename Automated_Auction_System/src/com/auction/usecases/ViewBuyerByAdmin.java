package com.auction.usecases;

import java.util.List;

import com.auction.Dao.AdminDao;
import com.auction.Dao.AdminDaoImpl;
import com.auction.exception.BuyerException;
import com.auction.model.Buyer;

public class ViewBuyerByAdmin {
	
	public static void main(String[] args) {
		
		
		
		AdminDao dao =new AdminDaoImpl();
		try {
			List<Buyer> ls =	dao.viewBuyers();
			ls.forEach((buyer) -> System.out.println(buyer));
			
		} catch (BuyerException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
