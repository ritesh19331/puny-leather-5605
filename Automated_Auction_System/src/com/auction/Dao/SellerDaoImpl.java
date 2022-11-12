package com.auction.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.auction.exception.AdminException;
import com.auction.exception.BuyerException;
import com.auction.exception.ItemException;
import com.auction.exception.SellerException;
import com.auction.model.Buyer;
import com.auction.model.Item;
import com.auction.model.Seller;
import com.auction.model.SoldItem;
import com.auction.utility.CurrentLogin;
import com.auction.utility.DBUtil;
import com.auction.utility.StatusChange;

public class SellerDaoImpl implements SellerDao {

	@Override
	public String registerAsSeller(Seller s) throws SellerException {
		String message="Seller Not Registered...";
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("insert into registered_seller(seller_name,email,password) values(?,?,?)");
		
		ps.setString(1, s.getSeller_name());
		ps.setString(2, s.getEmail());
		ps.setString(3, s.getPassword());
		
		int x = ps.executeUpdate();
		
		if(x>0) {
			message = s.getSeller_name()+" Registere successfully";
		}else
			throw new SellerException("Seller Not Registered...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String addNewItem(Item item) throws ItemException {
		String message="Item not Added...";
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("insert into item(item_name,price,quantity,category,seller_id,auction_end_time) values(?,?,?,?,?,?)");
		
		int seller_id = CurrentLogin.currentSellerLogin().getSeller_id();
		LocalDateTime ldt = LocalDateTime.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(ldt.toLocalDate());
		
		ps.setString(1, item.getItem_name());
		ps.setInt(2, item.getPrice());
		ps.setInt(3, item.getQuantity());
		ps.setString(4, item.getCategory());
		ps.setInt(5, seller_id);
		ps.setDate(6, sqlDate);
		
		int x = ps.executeUpdate();
		
		if(x>0) {
			message = "New item added";
		}else
			throw new ItemException(message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String updateItem(int item_id, int new_price, int new_quantity) throws ItemException {
		String message ="Item not found with id ..."+item_id;
		
		try (Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("update item set price = ? , quantity = ? where item_id = ?");
		
		ps.setInt(1, new_price);
		ps.setInt(2, new_quantity);
		ps.setInt(3, item_id);
		
		int x = ps.executeUpdate();
		if(x>0) {
			message = "Item updated...";
		} else
			throw new ItemException(message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return message;
	}

	@Override
	public String removeItem(int item_id) throws ItemException {
		String message ="Item you want to delete does not exist ...";
		
		try (Connection conn = DBUtil.provideConnection()) {
		PreparedStatement ps1 =	conn.prepareStatement("select * from item where item_id = ?");
		PreparedStatement ps =	conn.prepareStatement("delete from item where item_id = ? and seller_id =?");
		ps1.setInt(1, item_id);
		ResultSet rs1 =ps1.executeQuery();
		if(rs1.next()) {
			message="You Are Not Authorised To Delete This Item...";
		}
		int seller_id = CurrentLogin.currentSellerLogin().getSeller_id();
		
		ps.setInt(1, item_id);
		ps.setInt(2, seller_id);
		
		int x = ps.executeUpdate();
		if(x>0) {
			message = "Item deleted...";
		} else
			throw new ItemException(message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<SoldItem> soldItemList() throws ItemException {
		List<SoldItem> ls = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("select * from sold_item where seller_id = ?");
		
		int seller_id = CurrentLogin.currentSellerLogin().getSeller_id();
		
		ps.setInt(1,seller_id);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
//				
			int item_id = rs.getInt("item_id");
			String item_name = rs.getString("item_name");
			int trade_price = rs.getInt("traded_price");
			int buyer_id = rs.getInt("buyer_id");
			int sid = rs.getInt("seller_id");
			
			SoldItem si = new SoldItem(item_id, item_name, trade_price, buyer_id, sid);
			
			ls.add(si);
		}
		
		if(ls.size()==0) {
			throw new ItemException("Make Your First Sale To View Sold Items");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	public String loginAsSeller(String email, String password) throws SellerException {
		String message = "Login Attempt Failed...";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("select * from registered_seller where email = ? and password = ?");
		
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet rs =  ps.executeQuery();
		
		if(rs.next()) {
			message = "Login Successfull...";
			System.out.println(message);
			StatusChange.switchStatus("seller", "online",email);
			message="welcome";
		} else {
			throw new SellerException(message);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	
}
