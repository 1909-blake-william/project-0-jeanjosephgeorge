package com.revature.prompt;

import java.util.Scanner;

public class BankMenuPrompt implements Prompt {

	@Override
	public Prompt run() {
		Scanner input = new Scanner(System.in);
		String value;


		System.out.println("\n:.:.:.:.:.:.:.:.:.:.:.:.:.:\n");
		System.out.println("Hello " + LoginPrompt.username);
		System.out.println("Welcome.\n\n:.:.:.:.:.:.:.:.:.:.:.:.:.:\n\nPlease select appropriate option:");
		System.out.println(
				"1. View Accounts\n2. Add Accounts\n3. Transaction Menu\n4. Remove Account");
		System.out.println("5. Logout");
		value = input.nextLine();

		switch (value) {
		case "1":
			return new ViewAccountPrompt();
		case "2":
			return new AddSubAccount();
		case "3":
			return new TransactionPrompt();
		case "4":
			return new RemoveAccountPrompt();
		case "5":
			LoginPrompt.username = "";
			return new MainMenuPrompt();

		default:
			return this;
		}
	}

}
