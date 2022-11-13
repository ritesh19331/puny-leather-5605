package com.auction.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusChange {
	
	
	public static String switchStatus(String person,String new_st,String email) {
		String old_st="ONLINE";
		if(new_st.equalsIgnoreCase("OFFLINE"))
			old_st="ONLINE";
		String message ="Status " +old_st +"...";
		
		try (Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("update registered_"+person+" set status = ? where email = ?");
		ps.setString(1, new_st);
		ps.setString(2, email);
		
		int x = ps.executeUpdate();
		if(x>0) {
			message = "Status Changed : You Are " + new_st + ".. now.";
			if(new_st=="offline")
				System.out.println(ConsoleColors.ANSI_RED+message+ConsoleColors.ANSI_RESET);
			else
				System.out.println(ConsoleColors.GREEN_BACKGROUND+message+ConsoleColors.RESET);
//				System.out.println(message);
		} else
			System.out.println("No user found online...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return message;
	}
}
