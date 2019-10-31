package com.revature.util;

import com.revature.dao.BankDao;
import com.revature.model.Account;
import com.revature.model.SubAccount;

public class AuthUtil {

	public static final AuthUtil instance = new AuthUtil();
	
	private BankDao userDao = BankDao.currentImplementation;
	private Account currentUser = null;
	
	private AuthUtil(){
		super();
	}
	
	public Account login(String username, String password) {
		Account u = userDao.findByUsernameAndPassword(username, password);
		currentUser = u;
		return u;
	}

	public Account getCurrentUser() {
		return currentUser;
	}
	
	
}
