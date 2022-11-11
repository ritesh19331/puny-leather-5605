package com.auction.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	
	public static Connection provideConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn =	DriverManager.getConnection("jdbc:mysql://localhost:3306/auction_system","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
		
	}
}
