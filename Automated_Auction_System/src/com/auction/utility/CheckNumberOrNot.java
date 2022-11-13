package com.auction.utility;

public class CheckNumberOrNot {
	public static boolean numberOrNot(String st) {
		
		try {
			int x = Integer.parseInt(st);
			return true;
		} catch (NumberFormatException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND+"Can't process that ! Hint: Use NUMBER Format to enter you choice"+ConsoleColors.RESET);
		}
		
		return false;
	}
	 
}
