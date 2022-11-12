package com.auction.usecases;

import java.util.List;

import com.auction.Dao.BuyerDao;
import com.auction.Dao.BuyerDaoImpl;
import com.auction.exception.BuyerException;
import com.auction.exception.ItemException;
import com.auction.model.ItemRequest;

public class ViewOtherBuyerForItemOfferedBid {
	public static void main(String[] args) {
		
		BuyerDao dao = new BuyerDaoImpl();
		
		try {
			List<ItemRequest> ls =dao.viewOtherBuyersForBidItem();
			ls.forEach((item)->System.out.println(item));
		} catch (ItemException e) {
			System.out.println(e.getMessage());
		} catch (BuyerException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
