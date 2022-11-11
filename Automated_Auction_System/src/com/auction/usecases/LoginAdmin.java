package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.AdminDao;
import com.auction.Dao.AdminDaoImpl;
import com.auction.exception.AdminException;

public class LoginAdmin {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Admin Email");
		String email = sc.next();
		
		System.out.println("Enter Admin AccessKey");
		String password = sc.next();
		
		AdminDao dao =new AdminDaoImpl();
		try {
			String result = dao.LoginAdmin(email, password);
			System.out.println(result);
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
			
	}
}
