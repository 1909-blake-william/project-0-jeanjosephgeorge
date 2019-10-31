package com.revature.prompt;

import java.util.Scanner;

public class NewUserPrompt implements Prompt {

	@Override
	public Prompt run() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("New User Creation Menu");
		System.out.println("First Name:");
		System.out.println("Last Name:");
		
		return null;
	}

}
