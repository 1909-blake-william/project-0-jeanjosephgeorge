package com.revature.model;

public class Transaction {
	int recordId;
	int userId;
	int accountId;
	String transactionType;
	int transactionAmount;
	String time;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int recordId, int userId, int accountId, String transactionType, int transactionAmount,
			String time) {
		super();
		this.recordId = recordId;
		this.userId = userId;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.time = time;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + recordId;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + transactionAmount;
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + userId;
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
		Transaction other = (Transaction) obj;
		if (accountId != other.accountId)
			return false;
		if (recordId != other.recordId)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (transactionAmount != other.transactionAmount)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction ID: " + recordId + " | User ID: " + userId + " | AccountID=" + accountId
				+ " | Transaction Type: " + transactionType.toUpperCase() + " | Transaction Amount: " + transactionAmount + " | Time: " + time;
	}

}
