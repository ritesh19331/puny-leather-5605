package com.auction.usecases;

import java.util.Scanner;


import com.auction.Dao.SellerDao;
import com.auction.Dao.SellerDaoImpl;
import com.auction.exception.SellerException;

public class SellerLogin {
	
	public static boolean main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter Seller Email");
		String email = sc.next();
		
		System.out.println("Enter Password");
		String password = sc.next();
		
		
		SellerDao dao = new SellerDaoImpl();
		try {
			String result = dao.loginAsSeller(email, password);
			if(result.equals("welcome"))
				return true;
			else
				return false;
		} catch (SellerException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
}
