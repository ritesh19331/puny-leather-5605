package com.auction.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.auction.exception.BuyerException;
import com.auction.exception.ItemException;
import com.auction.exception.SellerException;
import com.auction.model.Buyer;
import com.auction.model.Item;
import com.auction.utility.DBUtil;
import com.auction.utility.StatusChange;

public class BuyerDaoImpl implements BuyerDao{

	@Override
	public String registerAsBuyer(Buyer buyer) throws BuyerException {
		String message="Buyer not Registered...";
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("insert into registered_buyer(buyer_name,email,password) values(?,?,?)");
		
		ps.setString(1, buyer.getBuyer_name());
		ps.setString(2, buyer.getEmail());
		ps.setString(3, buyer.getPassword());
		
		
		int x = ps.executeUpdate();
		
		if(x>0) {
			message = buyer.getBuyer_name()+" Registered successfully";
		}else
			throw new BuyerException("Buyer Not Registered...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;	
	}

	@Override
	public String loginAsBuyer(String email, String password) throws BuyerException {
		String message = "Login Attempt Failed...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("select * from seller where email = ? and password = ?");
		
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet rs =  ps.executeQuery();
		
		if(rs.next()) {
			message = "Login Successfull...";
			StatusChange.switchStatus("buyer", "online",email);
		} else {
			throw new BuyerException(message);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<Item> viewItemByCategory(String category) throws ItemException, BuyerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buyItem(int item_id) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

}
