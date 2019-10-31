package com.revature.dao;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.SubAccount;
import com.revature.model.Transaction;

public interface BankDao {

	BankDao currentImplementation = new BankDaoSQL();
	
	List<Account> findAll();
	
	List<SubAccount> findAllSubAccount();
	
	Account findByUsername(String username);
	
	Account findByUsernameAndPassword(String username, String password);
	
	int findUserId(String username);
	
	List<Integer> findAccounts(int userId);
	
	List<SubAccount> findSubAccount(String username);

	int save(Account a);
	
	int saveSubAccount(SubAccount sa);
	
	int getBalance(int accountId);
	
	int withdrawMoney(int newBalance, int withdrawAmount, int accountId, int userId);
	
	int depositMoney(int newBalance, int withdrawAmount, int accountId, int userId);
	
	List<Transaction> userTransaction(int accountId);
	
	int deleteAccount(int accountId);
	
	String getAccountStatus(int accountId);
	
}
