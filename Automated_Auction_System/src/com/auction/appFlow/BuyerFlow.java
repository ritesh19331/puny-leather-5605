package com.auction.appFlow;

import java.util.Scanner;

import com.auction.model.Buyer;
import com.auction.usecases.BuyerLogin;
import com.auction.usecases.BuyerRegister;
import com.auction.usecases.BuyerViewAllItem;
import com.auction.usecases.BuyerViewItemByCategory;
import com.auction.usecases.SelectItemToBuy;
import com.auction.usecases.ViewOtherBuyerForItemOfferedBid;
import com.auction.utility.CheckNumberOrNot;
import com.auction.utility.CurrentLogin;
import com.auction.utility.StatusChange;

public class BuyerFlow {
	
	public static void useAsBuyer() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("*****************************************");
			System.out.println();
			System.out.println("1. Register Buyer");
			System.out.println("2. Login Buyer");
			System.out.println("3. Go Back");
			System.out.println("4. Exit App");
			
			String s = sc.next();
			int x;
			if(CheckNumberOrNot.numberOrNot(s)==true)
				x=Integer.parseInt(s);
			else
				x=Integer.MAX_VALUE;
			
			if(x==1) {
				BuyerRegister.main(null);
			} else if(x==2) {
				boolean b = BuyerLogin.main(null);
				if(b) {
					BuyerFlow.BuyerOption();
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
	
	public static void BuyerOption() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("*****************************************");
			System.out.println();
			System.out.println("1. Search Item By Category");
			System.out.println("2. View Other Buyers For Item You Offered Bid Price");
			System.out.println("3. View All Items");
			System.out.println("4. Select Items To Buy");
			System.out.println("5. Go Back");
			System.out.println("6. Exit App");
			System.out.println("Enter Your Choice :");
			String s = sc.next();
			int x;
			if(CheckNumberOrNot.numberOrNot(s)==true)
				x=Integer.parseInt(s);
			else
				x=Integer.MAX_VALUE;
			
			if(x==1) {
				BuyerViewItemByCategory.main(null);
			}else if(x==2) {
				ViewOtherBuyerForItemOfferedBid.main(null);
			}else if(x==3) {
				BuyerViewAllItem.main(null);
			}else if(x==4) {
				SelectItemToBuy.main(null);
			}else if(x==5) {
				Buyer b = CurrentLogin.currentBuyerLogin();
				String status = b.getStatus();
				StatusChange.switchStatus("buyer", "offline", b.getEmail());
				BuyerFlow.useAsBuyer();
				break;
			}else if(x==6) { 
				Buyer b = CurrentLogin.currentBuyerLogin();
				String status = b.getStatus();
				StatusChange.switchStatus("buyer", "offline", b.getEmail());
				System.out.println("Thank You ! For Choosing Punny Leather Auction. Have a Nice Day");
				return;
			}else {
				System.out.println("Please Use The Below List Of Number Beside Each Option");
			}
		}
	}
}
