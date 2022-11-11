package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.BuyerDao;
import com.auction.Dao.BuyerDaoImpl;
import com.auction.exception.BuyerException;
import com.auction.model.Buyer;

public class BuyerRegister {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Buyer Name");
		String name = sc.next();
		
		System.out.println("Enter Buyer Email");
		String email = sc.next();
		
		System.out.println("Enter New Password");
		String password = sc.next();
		
		Buyer b = new Buyer(name,email,password);
		
		BuyerDao dao = new BuyerDaoImpl();
		
		try {
			System.out.println(dao.registerAsBuyer(b));
		} catch (BuyerException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
