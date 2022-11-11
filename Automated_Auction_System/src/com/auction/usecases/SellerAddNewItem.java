package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.SellerDao;
import com.auction.Dao.SellerDaoImpl;
import com.auction.exception.ItemException;
import com.auction.model.Item;

public class SellerAddNewItem {
	
	public static void main(String[] args) {
		
			Scanner sc = new  Scanner(System.in);
			System.out.println("Enter item name");
			String item_name = sc.next();
			
			System.out.println("Enter item price");
			int price = sc.nextInt();
			
			System.out.println("Enter item quantity");
			int quantity = sc.nextInt();
			
			System.out.println("Enter item category");
			String category = sc.next();
			
			Item item = new Item(item_name, price, quantity, category);
			
			SellerDao dao =new SellerDaoImpl();
			
			try {
				System.out.println(dao.addNewItem(item));
			} catch (ItemException e) {
				System.out.println(e.getMessage());
			}
		} 
}
