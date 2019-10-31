package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.BankDao;

public class RemoveAccountPrompt implements Prompt {

	BankDao bankDao = BankDao.currentImplementation;
	String username = LoginPrompt.username;
	List<Integer> accounts = bankDao.findAccounts(bankDao.findUserId(username));
	Scanner value = new Scanner(System.in);

	@Override
	public Prompt run() {
		System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:\n");
		System.out.println("Here are your active accounts, " + username + "...\n");

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
		System.out.println("> Press Enter the Account ID of account you wish to disable:");
		String option = value.nextLine();
		int number = Integer.parseInt(option);

		if (accounts.contains(number)) {
			System.out.println("Account Found\nDeleting now...\n");
			bankDao.deleteAccount(number);
			return this;
		} else {
			System.out.println("This account does not exist.\n");
			return this;
		}

	}

}
