package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.BankDao;
import com.revature.model.Account;
import com.revature.util.AuthUtil;

public class AdminPagePrompt implements Prompt {

	Scanner scan = new Scanner(System.in);
	BankDao bankDao = BankDao.currentImplementation;
	private AuthUtil authUtil = AuthUtil.instance;
	public static String username = "";
	
	@Override
	public Prompt run() {
		System.out.println("\nWelcome to the Admin Page.\nPlease enter username");
		String username = scan.nextLine();
		Account a = bankDao.findByUsername(username);
		String userType = a.getUsertype();

		switch (userType) {
		case "admin":
			break;

		case "user":
			System.out.println("Invalid credentials");
			System.out.println("Exiting to Main Login Page\n");
			return new MainMenuPrompt();
		default:
			break;
		}

		System.out.println("Enter Password");
		String password = scan.nextLine();
		Account u = authUtil.login(username, password);

		if (u == null) {
			System.out.println("\n:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:\n");
			System.out.println("Invalid Credentials!\n");
			System.out.println(":-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:\n");
			String choice = scan.nextLine();
			System.out.println("\n");
			return new LoginPrompt();
		} else {
			return new AdminControlPrompt();
		}

	}

}
