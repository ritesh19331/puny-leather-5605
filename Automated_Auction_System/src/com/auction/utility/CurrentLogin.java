package com.auction.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.auction.model.Buyer;
import com.auction.model.Seller;

public class CurrentLogin {
	
	public static Seller currentSellerLogin() {
		Seller s = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			
		PreparedStatement ps =	conn.prepareStatement("select * from buyer where status = ?");
			
		ps.setString(1, "online");
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int id = rs.getInt("sid");
			String name = rs.getString("seller_name");
			String email = rs.getString("email");
			String status = rs.getString("status");
			
			s = new Seller(id, name, email, email, status);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return s;
		
	}
}
