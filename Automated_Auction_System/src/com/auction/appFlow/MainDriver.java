package com.auction.appFlow;

import java.util.Scanner;

import com.auction.usecases.LoginAdmin;
import com.auction.usecases.ViewBuyerByAdmin;
import com.auction.usecases.ViewSellerByAdmin;
import com.auction.utility.CheckNumberOrNot;

public class MainDriver {
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("*****************************************");
		System.out.println("Welcome to Punny Leather Auction System");
		System.out.println("*****************************************");
		System.out.println();
		MainDriver.HomePage();
		
		
	}
	
	public static void HomePage() {
		
		Scanner sc =new Scanner(System.in);
		while(true) {
			System.out.println("1. Use As Admin");
			System.out.println("2. Use As Buyer");
			System.out.println("3. Use As Seller");
			System.out.println("4. Exit App");
			System.out.println("Enter your Choice : ");
			String s = sc.next();
			int x=0;
			if(CheckNumberOrNot.numberOrNot(s)==true)
				x=Integer.parseInt(s);
			else
				x=Integer.MAX_VALUE;
			
				if(x==1) {
					AdminFlow.useAsAdmin();
					break;
				} else if(x==2) {
					BuyerFlow.useAsBuyer();
					break;
				} else if(x==3) {
					SellerFlow.useAsSeller();
					break;
				} else if(x==4) {
					System.out.println("Thank You ! For Choosing Punny Leather Auction. Have a Nice Day");
					return;
				} else {
					System.out.println("Please Use The Below List Of Number Beside Each Option");
				}
			
			
		}
	}
	
	
	
}
