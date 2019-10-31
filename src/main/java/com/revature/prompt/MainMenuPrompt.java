package com.revature.prompt;

import java.util.Scanner;

public class MainMenuPrompt implements Prompt{

	public Prompt run() {
		Scanner input = new Scanner(System.in);
		String value;
		
		System.out.println("\n:--:---:--:-:--:---:--:");
		System.out.println(": Welcome to The Bank :");
		System.out.println(":--:---:--:-:--:---:--:\n");
		System.out.println("Press 1 for Main Menu");
		System.out.println("Press 2 if you are Admin\n");
		value = input.nextLine();
		
		switch (value) {
		case "1":
			return new LoginPrompt();
		case "2":
			return new AdminPagePrompt();
		default:
			break;
		}
		
		return new MainMenuPrompt();
	}

}
