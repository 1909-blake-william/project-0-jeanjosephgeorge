package com.revature.driver;

import com.revature.prompt.MainMenuPrompt;
import com.revature.prompt.Prompt;

public class BankDriver {

	public static void main(String[] args) {

		Prompt p = new MainMenuPrompt();

		while (true) {
			p = p.run();
		}
	}

}
