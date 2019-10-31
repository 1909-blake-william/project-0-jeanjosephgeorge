package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.BankDao;
import com.revature.model.SubAccount;

public class AddSubAccount implements Prompt {

	String username = LoginPrompt.username;
	private BankDao userDao = BankDao.currentImplementation;
	Scanner value = new Scanner(System.in);

	@Override
	public Prompt run() {
		String account_type = "";
		int userId = userDao.findUserId(username);
		System.out.println("\n\n:: Your UserID" + userId + " ::\n\n");
		
		System.out.println("Welcome to the New Account Menu");
		System.out.println("Choose 1 for Checking\nChoose 2 for Savings");
		String choice = value.nextLine();		

		switch (choice) {
		case "1":
			account_type = "checking";
			break;

		case "2":
			account_type = "savings";
			break;
		default:
			System.out.println("Invalid Option");
			return this;
		}

		SubAccount newAccount = new SubAccount(0, userId, account_type, 0, "active");
		userDao.saveSubAccount(newAccount);
		

		return new BankMenuPrompt();
	}

}
