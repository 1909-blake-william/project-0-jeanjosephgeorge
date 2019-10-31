package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.BankDao;

public class TransactionPrompt implements Prompt {

	BankDao bankDao = BankDao.currentImplementation;
	String username = LoginPrompt.username;
	int userId = bankDao.findUserId(username);
	Scanner input = new Scanner(System.in);
	List<Integer> accounts = bankDao.findAccounts(bankDao.findUserId(username));

	@Override
	public Prompt run() {

		System.out.println("\nTransaction Menu\n");
		System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:\n");
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
		System.out.println("\n> Enter Account ID of the account you wish to interact with: ");
		String option = input.nextLine();
		int accountId = Integer.parseInt(option);
		
		// Checking for Valid Account
		if (accounts.contains(accountId)) {
			System.out.println("Account selection valid\n");
		} else {
			System.out.println("Invalid selection.\n");
			return this;
		}

		// Getting Balance
		int balance = bankDao.getBalance(accountId);
		System.out.println("Your current balance is $" + balance + ".\n");

		// Main Transaction Menu
		System.out.println("Please select appropriate option");
		System.out.println("1. Withdraw Cash");
		System.out.println("2. Deposit Cash");
		System.out.println("3. View All Transactions");
		System.out.println("4. Previous Menu");
		String value = input.nextLine();
		boolean run = true;

		switch (value) {

		// Withdraw
		case "1":
			run = true;
			while (run == true) {
				System.out.println("Enter Amount to Withdraw");
				int withdrawAmount = input.nextInt();
				input.nextLine();

				if (withdrawAmount < 0) {
					System.out.println("I see what you're doing. Don't do it.");
					run = true;
				} else if (balance - withdrawAmount <= 0) {
					System.out.println("Insufficient Funds");
					run = true;
				} else {
					System.out.println("Let's go!");
					run = false;
					int newBalance = balance - withdrawAmount;
					bankDao.withdrawMoney(newBalance, withdrawAmount, accountId, userId);
				}
			}
			return new BankMenuPrompt();

		// Deposit
		case "2":
			run = true;
			while (run == true) {
				System.out.println("Enter Amount to Deposit");
				int depositAmount = input.nextInt();
				input.nextLine();

				if (depositAmount < 0) {
					System.out.println("Invalid Amount");
					run = true;
				} else {
					System.out.println("Updating account...\n");
					run = false;
					int newBalance = balance + depositAmount;
					bankDao.depositMoney(newBalance, depositAmount, accountId, userId);
				}
			}
			return new BankMenuPrompt();

		case "3":
			bankDao.userTransaction(accountId).forEach(transaction -> {
				System.out.println(transaction);
			});
			System.out.println("\nPress Enter to Exit to Main Menu");
			String exit = input.nextLine();
			return new BankMenuPrompt();

		case "4":
			return new BankMenuPrompt();

		default:
			System.out.println("\n:: Invalid Selection\n:: Menu Will Be Reloaded\n");
			return this;
		}

	}

}
