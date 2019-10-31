package com.revature.model;

public class SubAccount {
	int account_id;
	int user_id;
	String account_type;
	int account_balance;
	String account_status;
	public SubAccount(int account_id, int user_id, String account_type, int account_balance, String account_status) {
		super();
		this.account_id = account_id;
		this.user_id = user_id;
		this.account_type = account_type;
		this.account_balance = account_balance;
		this.account_status = account_status;
	}
	public SubAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public int getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(int account_balance) {
		this.account_balance = account_balance;
	}
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account_balance;
		result = prime * result + account_id;
		result = prime * result + ((account_status == null) ? 0 : account_status.hashCode());
		result = prime * result + ((account_type == null) ? 0 : account_type.hashCode());
		result = prime * result + user_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubAccount other = (SubAccount) obj;
		if (account_balance != other.account_balance)
			return false;
		if (account_id != other.account_id)
			return false;
		if (account_status == null) {
			if (other.account_status != null)
				return false;
		} else if (!account_status.equals(other.account_status))
			return false;
		if (account_type == null) {
			if (other.account_type != null)
				return false;
		} else if (!account_type.equals(other.account_type))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account ID: " + account_id + " | UserID: " + user_id + " | Type: " + account_type.toUpperCase()
				+ " | Balance: $" + account_balance + " | Account Status: " + account_status.toUpperCase();
	}

	
}
