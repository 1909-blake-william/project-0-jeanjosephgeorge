package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.BankDao;

public class AdminControlPrompt implements Prompt {
	BankDao bankDao = BankDao.currentImplementation;
	String username = AdminPagePrompt.username;
	Scanner scan = new Scanner(System.in);

	@Override
	public Prompt run() {

		System.out.println("\nPlease Select Option");
		System.out.println("1. View All Users\n2. View All Accounts\n3. View All Transactions\n4. Change User Access\n5. Logout");
		String value = scan.nextLine();

		switch (value) {
		case "1":
			bankDao.findAll().forEach(account -> {
				System.out.println(account);
			});
			return this;
		case "2":
			bankDao.findAllSubAccount().forEach(subAccount -> {
				System.out.println(subAccount);
			});
			return this;
		case "3":
			System.out.println("Not Implemented yet.\n");
			return this;
		case "4":
			System.out.println("Not Implemented yet.\n");
			return this;
		case "5":
			return new MainMenuPrompt();

		default:
			System.out.println("Invalid Selection.\n");
			return this;
		}

	}

}
