package com.auction.usecases;

import com.auction.Dao.AdminDao;
import com.auction.Dao.AdminDaoImpl;
import com.auction.exception.BuyerException;
import com.auction.exception.SellerException;

public class AdminSolveDisputes {
	public static void main(String[] args) {
		
		
		AdminDao dao =new AdminDaoImpl();
		
		try {
			System.out.println(dao.SolveDispute());
		} catch (BuyerException e) {
			System.out.println(e.getMessage());
		} catch (SellerException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
