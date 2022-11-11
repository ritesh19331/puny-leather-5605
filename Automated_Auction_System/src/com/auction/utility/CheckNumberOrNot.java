package com.auction.utility;

public class CheckNumberOrNot {
	public static boolean numberOrNot(String st) {
		
		try {
			int x = Integer.parseInt(st);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Can't process that ! Hint: Use NUMBER Format to enter you choice");
		}
		
		return false;
	}
	 
}
