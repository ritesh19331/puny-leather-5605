package com.auction.usecases;

import java.util.Scanner;

import com.auction.Dao.AdminDao;
import com.auction.Dao.AdminDaoImpl;
import com.auction.exception.AdminException;
import com.auction.model.Admin;

public class RegisterAdmin {
	public static void main(String[] args) {
		
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Enter name : ");
		String name =sc.nextLine();
		
		System.out.println("Enter email : ");
		String email =sc.next();
		
		System.out.println("Enter password : ");
		String password =sc.next();
		
		Admin a = new Admin(name, email, password);
		AdminDao dao = new AdminDaoImpl();
		
		try {
			System.out.println(dao.registerAdmin(a));;
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
