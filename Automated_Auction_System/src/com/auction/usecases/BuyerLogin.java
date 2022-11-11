package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.BuyerDao;
import com.auction.Dao.BuyerDaoImpl;
import com.auction.exception.BuyerException;

public class BuyerLogin {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter Buyer Email");
		String email = sc.next();
		
		System.out.println("Enter New Password");
		String password = sc.next();
		
		
		BuyerDao dao = new BuyerDaoImpl();
		try {
			System.out.println(dao.loginAsBuyer(email, password));
		} catch (BuyerException e) {
			System.out.println(e.getMessage());
		}
	}
}
