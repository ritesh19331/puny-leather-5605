package com.auction.usecases;

import java.util.List;

import com.auction.Dao.BuyerDao;
import com.auction.Dao.BuyerDaoImpl;
import com.auction.exception.ItemException;
import com.auction.model.Item;

public class BuyerViewAllItem {
	public static void main(String[] args) {
		
		BuyerDao dao =new BuyerDaoImpl();
		
		try {
			List<Item> ls =dao.viewAllItem();
			ls.forEach((item) -> System.out.println(item));
		} catch (ItemException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
