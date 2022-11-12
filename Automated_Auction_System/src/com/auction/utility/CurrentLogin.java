package com.auction.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.auction.model.Buyer;
import com.auction.model.Seller;

public class CurrentLogin {
	
	public static Seller currentSellerLogin() {
		Seller s = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			
		PreparedStatement ps =	conn.prepareStatement("select * from registered_seller where status = ?");
			
		ps.setString(1, "online");
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int id = rs.getInt("sid");
			String name = rs.getString("seller_name");
			String email = rs.getString("email");
			String status = rs.getString("status");
			
			s = new Seller(id, name, email, email, status);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return s;
		
	}
	
	public static Buyer currentBuyerLogin() {
		Buyer s = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			
		PreparedStatement ps =	conn.prepareStatement("select * from registered_buyer where status = ?");
			
		ps.setString(1, "online");
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int id = rs.getInt("bid");
			String name = rs.getString("buyer_name");
			String email = rs.getString("email");
			String status = rs.getString("status");
			
			s = new Buyer(id, name, email,null,status);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return s;

	}
}
