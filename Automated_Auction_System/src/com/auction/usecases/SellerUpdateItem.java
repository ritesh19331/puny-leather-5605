package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.SellerDao;
import com.auction.Dao.SellerDaoImpl;
import com.auction.exception.ItemException;

public class SellerUpdateItem {
	public static void main(String[] args) {
		
			Scanner sc = new  Scanner(System.in);
			System.out.println("Enter item_id");
			int item_id = sc.nextInt();
			
			System.out.println("Enter new item price");
			int price = sc.nextInt();
			
			System.out.println("Enter new item quantity");
			int quantity = sc.nextInt();
			
			
			SellerDao dao =new SellerDaoImpl();
			
			try {
				System.out.println(dao.updateItem(item_id, price, quantity));
			} catch (ItemException e) {
				System.out.println(e.getMessage());
			}
		} 
		
}
