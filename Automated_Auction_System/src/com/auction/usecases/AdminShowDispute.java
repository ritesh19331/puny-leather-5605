package com.auction.usecases;

import java.util.List;

import com.auction.Dao.AdminDao;
import com.auction.Dao.AdminDaoImpl;
import com.auction.exception.BuyerException;
import com.auction.model.DisputeItem;
import com.auction.utility.ConsoleColors;

public class AdminShowDispute {
	public static void main(String[] args) {
		
		AdminDao dao =new AdminDaoImpl();
		
		try {
			List<DisputeItem> list =	dao.ShowDispute();
			list.forEach((ls) -> System.out.println(ls));
		} catch (BuyerException e) {
			System.out.println(ConsoleColors.GREEN_BACKGROUND+e.getMessage()+ConsoleColors.RESET);
		}
		
		
	}
}
