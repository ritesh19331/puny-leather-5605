package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.BuyerDao;
import com.auction.Dao.BuyerDaoImpl;
import com.auction.exception.BuyerException;

public class BuyerLogin {
	public static boolean main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter Buyer Email");
		String email = sc.next();
		
		System.out.println("Enter Login Password");
		String password = sc.next();
		
		
		BuyerDao dao = new BuyerDaoImpl();
		try {
			String s = dao.loginAsBuyer(email, password);
			if(s.equalsIgnoreCase("welcome"))
				return true;
			else
				return false;
		} catch (BuyerException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
}
