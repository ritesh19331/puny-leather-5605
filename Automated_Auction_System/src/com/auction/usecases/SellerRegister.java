package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.SellerDao;
import com.auction.Dao.SellerDaoImpl;
import com.auction.exception.SellerException;
import com.auction.model.Seller;

public class SellerRegister {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Seller Name");
		String name = sc.next();
		
		System.out.println("Enter Seller Email");
		String email = sc.next();
		
		System.out.println("Enter New Password");
		String password = sc.next();
		
		Seller s = new Seller(name,email,password);
		
		SellerDao dao = new SellerDaoImpl();
		
		
		
		try {
			System.out.println(dao.registerAsSeller(s));
		} catch (SellerException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
