package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.BankDao;
import com.revature.model.Account;
import com.revature.model.SubAccount;
import com.revature.util.AuthUtil;

public class LoginPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private BankDao userDao = BankDao.currentImplementation;
	private AuthUtil authUtil = AuthUtil.instance;
	public static String username = "";
	public static int user_id=0;
	
	@Override
	public Prompt run() {
		System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:\n");
		System.out.println("To Proceed, please choose:");
		System.out.println("Enter 1 to Login");
		System.out.println("Enter 2 to Register");
		System.out.println("Enter 3 for Previous page");
		String choice = scan.nextLine();
		switch (choice) {
		case "1": {
			System.out.println("Enter username");
			username = scan.nextLine();
			System.out.println("Enter password");
			String password = scan.nextLine();

			Account u = authUtil.login(username, password);
			if (u == null) {
				System.out.println("\n\n:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:\n");
				System.out.println("Invalid Credentials!");
				System.out.println("You know, you could create a new account?");
				System.out.println("Press any key & press Enter to continue.\n");
				System.out.println(":-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:\n");
				choice = scan.nextLine();
				System.out.println("\n");
				break;
			} else {
				
				System.out.println(username);
				return new BankMenuPrompt();
			}
		}

		case "2": {
			System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:");
			System.out.println("Welcome to the New User Creation Menu");
			System.out.println("Enter new username");
			String username = scan.nextLine();
			Account a = userDao.findByUsername(username);
			if (a != null) {
				System.out.println("That username is already taken\nPlease choose another one.\n");
				break;
			}
			System.out.println("Enter new Password:");
			String password = scan.nextLine();
			System.out.println("Enter First Name:");
			String firstname = scan.nextLine();
			System.out.println("Enter Last Name:");
			String lastname = scan.nextLine();
			System.out.println("Enter Address:");
			String address = scan.nextLine();
			Account newAccount = new Account(0, username, password, firstname, lastname, address, "user");
			userDao.save(newAccount);
			System.out.println("Account Created\nReturning to Main Menu...\n");
		}
		
		case "3": 
			return new MainMenuPrompt();

		default:
			System.out.println("Invalid Option");
			break;
		}

		return this;
	}

}
