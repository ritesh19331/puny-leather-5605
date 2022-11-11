package com.auction.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.auction.exception.AdminException;
import com.auction.exception.BuyerException;
import com.auction.exception.SellerException;
import com.auction.model.Admin;
import com.auction.model.Buyer;
import com.auction.model.Seller;
import com.auction.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public String loginAdmin(String username, String access_key) throws AdminException {
		String message="Admin not Registered...";
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("select * from admin where email = ? and password = ?");
		
		ps.setString(1, username);
		ps.setString(2, access_key);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
//			System.out.println();
			message = "Hey! " + rs.getString("admin_name")+", Admin Access Successfull ";
			System.out.println(message);
			message="welcome";
		}else
			throw new AdminException("Please Enter Correct Login And Password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<Buyer> viewBuyers() throws BuyerException {
		List<Buyer> ls = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =	conn.prepareStatement("select * from registered_buyer");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
//				
				int bid = rs.getInt("bid");
				String buyer_name = rs.getString("buyer_name");
				String email = rs.getString("email");
				
				Buyer b = new Buyer(bid,buyer_name,email);
				
				ls.add(b);
			}
			
			if(ls.size()==0) {
				throw new BuyerException("No Buyers Found");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return ls;
	}

	@Override
	public List<Seller> viewSellers() throws SellerException {
		List<Seller> ls = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =	conn.prepareStatement("select * from registered_seller");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
//				
				int bid = rs.getInt("sid");
				String buyer_name = rs.getString("seller_name");
				String email = rs.getString("email");
				
				Seller s = new Seller(bid,buyer_name,email);
				
				ls.add(s);
			}
			
			if(ls.size()==0) {
				throw new SellerException("No Sellers Found");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return ls;
	}

	@Override
	public String registerAdmin(Admin a) throws AdminException {
		String message="Admin not Registered...";
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("insert into admin(admin_name,email,password) values(?,?,?)");
		
		ps.setString(1, a.getAdmin_name());
		ps.setString(2, a.getEmail());
		ps.setString(3, a.getPassword());
		
		int x = ps.executeUpdate();
		
		if(x > 0) {
			message = " Registered As Admin Successfully ";
		}else
			throw new AdminException("Admin Registration Failed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	
}
