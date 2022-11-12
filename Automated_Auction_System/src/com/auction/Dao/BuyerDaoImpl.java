package com.auction.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auction.exception.BuyerException;
import com.auction.exception.ItemException;
import com.auction.exception.NotificationException;
import com.auction.exception.SellerException;
import com.auction.model.Buyer;
import com.auction.model.Item;
import com.auction.model.ItemRequest;
import com.auction.model.SoldItem;
import com.auction.utility.CurrentLogin;
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
		
		PreparedStatement ps =	conn.prepareStatement("select * from registered_buyer where email = ? and password = ?");
		
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet rs =  ps.executeQuery();
		
		if(rs.next()) {
			message = "Login Successfull...";
			System.out.println(message);
			message="welcome";
			StatusChange.switchStatus("buyer","online",email);
			
			int bid =CurrentLogin.currentBuyerLogin().getBid();
			PreparedStatement ps1 = conn.prepareStatement("select *  from buyer_notification where buyer_id = ?");
			ps1.setInt(1, bid);
			
			ResultSet rs1  = ps1.executeQuery();
			
			int count =0;
			while(rs1.next()) {
				 count++;
			}
			if(count==1)
				System.out.println("You Have "+ count+" Unread Notification");
			else if(count>1)
				System.out.println("You Have "+ count+" Unread Notifications");
			
			
			
		} else {
			throw new BuyerException(message);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<Item> viewItemByCategory(String category) throws ItemException {
		List<Item> ls = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("select * from item where category = ?");
		
		ps.setString(1,category);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
//				
			int item_id = rs.getInt("item_id");
			String item_name = rs.getString("item_name");
			int price = rs.getInt("price");
			int seller_id = rs.getInt("seller_id");
			
			Item item = new Item(item_id, item_name, price, seller_id, category);
			
			ls.add(item);
		}
		
		if(ls.size()==0) {
			throw new ItemException("Not Item Found for the category : "+category);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	public String buyItem(int item_id,int offer_price) throws ItemException {
		String message = "Item not bought";
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from item where item_id = ?");
			ps.setInt(1, item_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int iid = rs.getInt("item_id");
				String item_name = rs.getString("item_name");
				int price = rs.getInt("price");
				int seller_id = rs.getInt("seller_id");
				String category = rs.getString("category");
				int best_price=0;
				{
					PreparedStatement ps2 = conn.prepareStatement(" select max(your_offer_price) from buy_request where item_id = ?");
					ps2.setInt(1, iid);
					ResultSet  rs2 = ps2.executeQuery();
					
					if(rs2.next()) {
						best_price=rs2.getInt("max(your_offer_price)");
//						System.out.println(best_price);
					}
				}
				if(best_price<offer_price) {
					best_price=offer_price;
					PreparedStatement ps3 = conn.prepareStatement("update buy_request set best_price_offered= ?");
					ps3.setInt(1, best_price);
					ps3.executeUpdate();
					
				}
				int buyer_id = CurrentLogin.currentBuyerLogin().getBid();
				Item item = new Item(iid, item_name, price, seller_id, category);
				
				PreparedStatement ps1 = conn.prepareStatement("insert into buy_request(item_id,"
						+ "item_name,seller_id,category,"
						+ "your_offer_price,best_price_offered,"
						+ "buyer_id) values(?,?,?,?,?,?,?)");
				
				
				ps1.setInt(1, iid);
				ps1.setString(2, item_name);
				ps1.setInt(3, seller_id);
				ps1.setString(4, category);
				ps1.setInt(5, offer_price);
				ps1.setInt(6, best_price);
				ps1.setInt(7, buyer_id);
//				LocalDateTime ldt = LocalDateTime.now();
//				java.sql.Date sqlDate = java.sql.Date.valueOf(ldt.toLocalDate());
//				ps1.setDate(7, sqlDate);
				
				int x =ps1.executeUpdate();
				
				if(x>0) {
					message="Item Buy Request Sent To Auctioning Authorities....";
				} else 
					throw new ItemException("Item Not Bought..");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return message;
	}

	@Override
	public List<ItemRequest> viewOtherBuyersForBidItem() throws ItemException, BuyerException {
		List<ItemRequest> ls = new ArrayList<>();
		try (Connection conn = DBUtil.provideConnection()) {
			
			
			PreparedStatement ps = conn.prepareStatement("select * from buy_request where buyer_id = ?");
			int bid =CurrentLogin.currentBuyerLogin().getBid();
			ps.setInt(1, bid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int item_id = rs.getInt("item_id");
				PreparedStatement ps1 = conn.prepareStatement("select * from buy_request where item_id = ?");
				ps1.setInt(1, item_id);
				ResultSet rs1 = ps1.executeQuery();
				
				while(rs1.next()) {
					int seller_id = rs1.getInt("seller_id");
					int buyer_id = rs1.getInt("buyer_id");
					String item_name = rs1.getString("item_name");
					String categroy = rs1.getString("category");
					int your_price = rs1.getInt("your_offer_price");
					int best_price_offered = rs1.getInt("best_price_offered");
					String datetime = rs1.getString("auction_end_time");
					
					ItemRequest ir = new  ItemRequest(seller_id, buyer_id, item_name,
														item_id, categroy, your_price,
														best_price_offered, datetime);
					ls.add(ir);
					
				}
			}
			if(ls.size()==0)
				throw new ItemException("Buy Request is Empty ! Offer A New Bid Price");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	public List<Item> viewAllItem() throws ItemException {
		List<Item> ls = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps =	conn.prepareStatement("select * from item ");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			int item_id = rs.getInt("item_id");
			String item_name = rs.getString("item_name");
			int price = rs.getInt("price");
			int seller_id = rs.getInt("seller_id");
			String category = rs.getString("category");
			
			Item item = new Item(item_id, item_name, price, seller_id, category);
			
			ls.add(item);
		}
		
		if(ls.size()==0) {
			throw new ItemException("No Item Found");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	public void readBuyerNotification() throws NotificationException {
		int bid =CurrentLogin.currentBuyerLogin().getBid();
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps1 = conn.prepareStatement("select * from buyer_notification where buyer_id = ? and status='unread'");
			ps1.setInt(1, bid);
			
			ResultSet rs1  = ps1.executeQuery();
			int flag=0;
			while(rs1.next()) {
				flag++;
				System.out.println(rs1.getString("message"));
			}
			if(flag==0)
				throw new NotificationException("No Unread Notification");
			
			PreparedStatement ps2 = conn.prepareStatement("update buyer_notification set status='read' where status='unread' and buyer_id=?");
			ps2.setInt(1, bid);
			
			int z  = ps2.executeUpdate();
			if(z==0)
				throw new NotificationException("No Unread Notification");
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

}
