package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.BuyerDao;
import com.auction.Dao.BuyerDaoImpl;
import com.auction.exception.ItemException;

public class SelectItemToBuy {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter item id");
		int item_id = sc.nextInt();
		
		System.out.println("Enter Price You Want To Offer");
		int price = sc.nextInt();
		
		
		BuyerDao dao =new BuyerDaoImpl();
		
		try {
			String result = dao.buyItem(item_id, price);
			System.out.println(result);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
