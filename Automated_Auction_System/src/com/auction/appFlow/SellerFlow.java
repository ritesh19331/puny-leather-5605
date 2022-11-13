package com.auction.appFlow;

import java.util.Scanner;

import com.auction.model.Seller;
import com.auction.usecases.SellerAddNewItem;
import com.auction.usecases.SellerLogin;
import com.auction.usecases.SellerRegister;
import com.auction.usecases.SellerRemoveItem;
import com.auction.usecases.SellerSoldItem;
import com.auction.usecases.SellerUpdateItem;
import com.auction.utility.CheckNumberOrNot;
import com.auction.utility.CurrentLogin;
import com.auction.utility.StatusChange;

public class SellerFlow {
	
	public static void useAsSeller() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("*****************************************");
			System.out.println();
			System.out.println("+----------------------+");
			System.out.println("|1. Register Seller    |");
			System.out.println("|2. Login Seller       |");
			System.out.println("|3. Go Back            |");
			System.out.println("|4. Exit App           |");
			System.out.println("+----------------------+");
			System.out.println("|Enter Your Choice :");
			
			String s = sc.next();
			System.out.println("*****************************************");
			int x;
			if(CheckNumberOrNot.numberOrNot(s)==true)
				x=Integer.parseInt(s);
			else
				x=Integer.MAX_VALUE;
			
			if(x==1) {
				SellerRegister.main(null);
			} else if(x==2) {
				boolean b = SellerLogin.main(null);
				if(b) {
					SellerFlow.sellerOption();
					break;
				}
			} else if(x==3) {
				MainDriver.HomePage();
				break;
			} else if(x==4) {
				System.out.println("Thank You ! For Choosing Punny Leather Auction. Have a Nice Day");
				return;
			} else {
				System.out.println("Please Use The Below List Of Number Beside Each Option");
			}
		}
	}
	
	public static void sellerOption() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("*****************************************");
			System.out.println();
			System.out.println("+----------------------------+");
			System.out.println("1. Add New Item              |");
			System.out.println("2. Update Item               |");
			System.out.println("3. Remove Item               |");
			System.out.println("4. View Sold Item History    |");
			System.out.println("5. Go Back                   |");
			System.out.println("6. Exit App                  |");
			System.out.println("+----------------------------+");
			System.out.println("Enter Your Choice :");
			String s = sc.next();
			System.out.println("*****************************************");
			int x;
			if(CheckNumberOrNot.numberOrNot(s)==true)
				x=Integer.parseInt(s);
			else
				x=Integer.MAX_VALUE;
			
			if(x==1) {
				SellerAddNewItem.main(null);
			}else if(x==2) {
				SellerUpdateItem.main(null);
			}else if(x==3) {
				SellerRemoveItem.main(null);
			}else if(x==4) {
				SellerSoldItem.main(null);
			}else if(x==5) {
				Seller seller = CurrentLogin.currentSellerLogin();
				String status = seller.getStatus();
				StatusChange.switchStatus("seller", "offline", seller.getEmail());
				SellerFlow.useAsSeller();
				break;
			}else if(x==6) { 
				Seller seller = CurrentLogin.currentSellerLogin();
				String status = seller.getStatus();
				StatusChange.switchStatus("seller", "offline", seller.getEmail());
				System.out.println("Thank You ! For Choosing Punny Leather Auction. Have a Nice Day");
				return;
			}else {
				System.out.println("Please Use The Below List Of Number Beside Each Option");
			}
		}
	}
}
