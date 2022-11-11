package com.auction.usecases;

import java.util.List;

import com.auction.Dao.SellerDao;
import com.auction.Dao.SellerDaoImpl;
import com.auction.exception.ItemException;
import com.auction.model.SoldItem;

public class SellerSoldItem {
	public static void main(String[] args) {

		
		SellerDao dao =new SellerDaoImpl();
		
		try {
			List<SoldItem> ls = dao.soldItemList();
			
			ls.forEach((item) -> System.out.println(item));
			
		} catch (ItemException e) {
			System.out.println(e.getMessage());
		} 
		
		
		
	}
}
