package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.SellerDao;
import com.auction.Dao.SellerDaoImpl;
import com.auction.exception.ItemException;

public class SellerRemoveItem {
	public static void main(String[] args) {
		
		Scanner sc =new  Scanner(System.in);
		
		
		System.out.println("Enter item_id");
		int item_id = sc.nextInt();
		
		SellerDao dao =new SellerDaoImpl();
		
		try {
			System.out.println(dao.removeItem(item_id));
		} catch (ItemException e) {
			System.out.println(e.getMessage());
		} 
		
		
	}
}
