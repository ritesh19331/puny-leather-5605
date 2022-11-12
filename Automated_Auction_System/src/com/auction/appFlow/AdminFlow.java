package com.auction.appFlow;

import java.util.Scanner;

import com.auction.usecases.AdminShowDispute;
import com.auction.usecases.AdminSolveDisputes;
import com.auction.usecases.LoginAdmin;
import com.auction.usecases.RegisterAdmin;
import com.auction.usecases.ViewBuyerByAdmin;
import com.auction.usecases.ViewSellerByAdmin;
import com.auction.utility.CheckNumberOrNot;

public class AdminFlow {
	public static void useAsAdmin() {
		
		Scanner sc =new Scanner(System.in);
		
		while(true) {
			System.out.println("*****************************************");
			System.out.println();
			System.out.println("1. Register Admin");
			System.out.println("2. Login Admin");
			System.out.println("3. Change User Type");
			System.out.println("4. Go Back");
			System.out.println("5. Exit App");
			System.out.println("Enter Your Choice :");
			String s = sc.next();
			int x;
			if(CheckNumberOrNot.numberOrNot(s)==true)
				x=Integer.parseInt(s);
			else
				x=Integer.MAX_VALUE;
				if(x==1) {
					RegisterAdmin.main(null);
				} else if(x==2) {
					boolean b = LoginAdmin.main(null);
					if(b) {
						AdminFlow.viewBuyerAndSellers();
						break;
					}
				} else if(x==3 || x==4){
					MainDriver.HomePage();
					break;
				} else if(x==5){
					System.out.println("Thank You ! For Choosing Punny Leather Auction. Have a Nice Day");
					return;
				} else {
					System.out.println("Please Use The Below List Of Number Beside Each Option");
				}
		}
	}
	
	public static void viewBuyerAndSellers() {
		
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("*****************************************");
			System.out.println();
			System.out.println("1. View Buyers");
			System.out.println("2. View Sellers");
			System.out.println("3. Change User Type");
			System.out.println("4. Show Disputes");
			System.out.println("5. Solve Disputes");
			System.out.println("6. Go Back");
			System.out.println("7. Exit App");
			System.out.println("Enter Your Choice :");
			String s = sc.next();
			int x;
			if(CheckNumberOrNot.numberOrNot(s)==true)
				x=Integer.parseInt(s);
			else
				x=Integer.MAX_VALUE;
			
			if(x==1) {
				ViewBuyerByAdmin.main(null);
			} else if(x==2) {
				ViewSellerByAdmin.main(null);
			} else if(x==3){
				MainDriver.HomePage();
				break;
			} else if(x==4) {
				AdminShowDispute.main(null);
			} else if(x==5) {
				AdminSolveDisputes.main(null);
			} else if(x==6) {
				AdminFlow.useAsAdmin();
				break;
			}else if(x==7) {
				System.out.println("Thank You ! For Choosing Punny Leather Auction. Have a Nice Day");
				return;
			} else {
				System.out.println("Please Use The Below List Of Number Beside Each Option");
			}
		}
	}
}
