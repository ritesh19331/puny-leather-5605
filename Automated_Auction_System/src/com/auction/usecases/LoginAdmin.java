package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.AdminDao;
import com.auction.Dao.AdminDaoImpl;
import com.auction.exception.AdminException;

public class LoginAdmin {
	public static boolean main(String[] args) {
		
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Admin Email");
			
			String email = sc.next();
			
			System.out.println("Enter Admin AccessKey");
			String password = sc.next();
			
			AdminDao dao =new AdminDaoImpl();
			try {
				String result = dao.loginAdmin(email, password);
				if(result.equals("welcome"))
					return true;
				else
					return false;
			} catch (AdminException e) {
				System.out.println(e.getMessage());
			}
			return false;
	}
}
