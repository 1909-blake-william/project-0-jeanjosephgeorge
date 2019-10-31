package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.BankDao;

public class ViewAccountPrompt implements Prompt {

	BankDao bankDao = BankDao.currentImplementation;
	String username = LoginPrompt.username;
	
	@Override
	public Prompt run() {
		Scanner value = new Scanner(System.in);

		System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:\n");
		System.out.println("Here are your active accounts, "+ username +"...\n");
		
		bankDao.findSubAccount(username).forEach(account -> {
			String status = account.getAccount_status();
			switch (status) {
			case "closed":

				break;
				
			case "active":
				System.out.println(account);

			default:
				break;
			}
		});
		
		System.out.println("\n\n:.:.:.:.:.:.:.:.:.:.:.:.:.:\n");
		System.out.println("> Press Enter to return to previous");
		String next = value.nextLine();
		
		return new BankMenuPrompt();
	}

}
