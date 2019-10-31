package com.revature.driver;

import com.revature.dao.BankDao;

public class ManualTestDriver {
	
	public static void main(String[] args) {
		
		BankDao bankDao = BankDao.currentImplementation;
		
		System.out.println("All User Accounts");
		
		bankDao.findAll().forEach(account -> {
			System.out.println(account);
		});
		
		
		
	}

}
