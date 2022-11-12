package com.auction.usecases;

import com.auction.Dao.BuyerDao;
import com.auction.Dao.BuyerDaoImpl;
import com.auction.exception.NotificationException;

public class BuyerNotification {
	public static void main(String[] args) {
		
		BuyerDao dao =new BuyerDaoImpl();
		
		try {
			dao.readBuyerNotification();
		} catch (NotificationException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
