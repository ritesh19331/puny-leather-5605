package com.auction.usecases;

import java.util.List;
import java.util.Scanner;

import com.auction.Dao.BuyerDao;
import com.auction.Dao.BuyerDaoImpl;
import com.auction.exception.ItemException;
import com.auction.model.Item;

public class BuyerViewItemByCategory {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Category Name");
		String category = sc.next();
		
		
		BuyerDao dao = new BuyerDaoImpl();
		
		try {
			List<Item> ls =dao.viewItemByCategory(category);
			
			ls.forEach((item) -> System.out.println(item));
			
		} catch (ItemException e) {
			System.out.println(e.getMessage());
		}

	}
}
