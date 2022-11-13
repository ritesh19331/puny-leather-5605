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
import com.auction.model.DisputeItem;
import com.auction.model.ItemRequest;
import com.auction.model.Seller;
import com.auction.model.SoldItem;
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
			message = "Hey! " + rs.getString("admin_name")+", Admin Access Granted ";
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

	@Override
	public List<DisputeItem> ShowDispute() throws BuyerException {
		List<DisputeItem> ls =new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			
		PreparedStatement ps =	conn.prepareStatement(" select seller_id,  item_name , count(buyer_id) ,"
									+ " item_id, buyer_id BuyerID_Who_Offered_Highest_Price,"
									+ "  max(your_offer_price)     from buy_request"
									+ " where buyer_id =(select buyer_id from "
									+ "buy_request where your_offer_price = ("
									+ "select max(your_offer_price) from buy_request)) "
									+ "group by item_id;");
		
		ResultSet rs =	ps.executeQuery();
		while(rs.next()) {
			String item_name =rs.getString("item_name");
			int item_id = rs.getInt("item_id");
			int seller_id = rs.getInt("seller_id");
			int max_price = rs.getInt("max(your_offer_price)");
			int buyer_id = rs.getInt("BuyerID_Who_Offered_Highest_Price");
			int totalBuyRequest = rs.getInt("count(buyer_id)");
			
			DisputeItem di = new DisputeItem(item_name, item_id, seller_id, max_price, buyer_id, totalBuyRequest);
			
			ls.add(di);
		}
		if(ls.size()==0)
			throw new BuyerException("No Dispute Available");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	public String SolveDispute() throws BuyerException, SellerException {
		String message = "No Dispute Available..";
		try(Connection conn = DBUtil.provideConnection()) {
			
			
			PreparedStatement ps =	conn.prepareStatement(" select seller_id,  item_name , count(buyer_id) ,"
										+ " item_id, buyer_id BuyerID_Who_Offered_Highest_Price,"
										+ "  max(your_offer_price)     from buy_request"
										+ " where buyer_id =(select buyer_id from "
										+ "buy_request where your_offer_price = ("
										+ "select max(your_offer_price) from buy_request)) "
										+ "group by item_id;");
			
			ResultSet rs =	ps.executeQuery();
			int count=0;
			while(rs.next()) {
				String item_name =rs.getString("item_name");
				int item_id = rs.getInt("item_id");
				int seller_id = rs.getInt("seller_id");
				int max_price = rs.getInt("max(your_offer_price)");
				int buyer_id = rs.getInt("BuyerID_Who_Offered_Highest_Price");
				int totalBuyRequest = rs.getInt("count(buyer_id)");
				
				SoldItem item = new SoldItem(item_id, item_name, max_price, buyer_id, seller_id);
				
				PreparedStatement ps1 = conn.prepareStatement("insert into sold_item(item_id,item_name,traded_price,buyer_id,seller_id) values(?,?,?,?,?)");
				
				ps1.setInt(1, item_id);
				ps1.setString(2, item_name);
				ps1.setInt(3, max_price);
				ps1.setInt(4, buyer_id);
				ps1.setInt(5, seller_id);
				int x = ps1.executeUpdate();
				if(x>0) {
					
					PreparedStatement ps2 = conn.prepareStatement("insert into buyer_notification(buyer_id,message) values(?,?)");
					ps2.setInt(1,buyer_id);
					String str ="Congrats ! You Won Auction For Item Id : "+item_id+" --"+item_name;
					ps2.setString(2, str);
					
					int bnotify = ps2.executeUpdate();
					if(bnotify>0) {
						System.out.println("+---------------------------------------------------------------------------------+");
						System.out.println("| Buyer With Buyer_Id : "+buyer_id +", Notification Sent For Winning The Auction |");
						System.out.println("+---------------------------------------------------------------------------------+");
						
					}
					PreparedStatement ps4 = conn.prepareStatement("select * from buy_request where item_id=? and buyer_id!=?");
					ps4.setInt(1, item_id);
					ps4.setInt(2, buyer_id);
					
					ResultSet rs4  = ps4.executeQuery();
					
					while(rs4.next()) {
						int struggler_id = rs.getInt("item_id");
						str="Auction Lost ! Item Was Sold For : "+max_price;
						
						//Inserting notification in buyer table for sending when they login
						
						PreparedStatement ps5  = conn.prepareStatement("insert into buyer_notification(buyer_id,message) values(?,?)");
						ps5.setInt(1, struggler_id);
						ps5.setString(2, str);
						int strgNotify = ps5.executeUpdate();
						if(strgNotify>0) {
							System.out.println("Lost Notification Sent To Buyer with id :" + struggler_id );
						}
					}
					
					//Deleting from Buy Request Table
					
					PreparedStatement ps3 = conn.prepareStatement("delete from buy_request where item_id = ?");
					ps3.setInt(1, item_id);
					int y = ps3.executeUpdate();
					count+=y;
					if(y>0) {
						message = "Disputes Solved.."+1;
						System.out.println("Disputes Solved for SellerID: "+seller_id);
						System.out.println();
					}
					
					//deleting from item table
					
					PreparedStatement ps5 = conn.prepareStatement("delete from item where item_id = ?");
					ps5.setInt(1, item_id);
					int checkps5 = ps5.executeUpdate();
					
				}
				
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return message;
	}
	
	
	
}
