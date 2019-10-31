package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.SubAccount;
import com.revature.model.Transaction;
import com.revature.util.ConnectionUtil;

public class BankDaoSQL implements BankDao {

	SubAccount extractSubAccount(ResultSet rs) throws SQLException {
		int rsAccountId = rs.getInt("account_id");
		int rsUserId = rs.getInt("user_id");
		String rsAccountType = rs.getString("account_type");
		int rsAccountBalance = rs.getInt("account_balance");
		String rsAccountStatus = rs.getString("account_status");
		return new SubAccount(rsAccountId, rsUserId, rsAccountType, rsAccountBalance, rsAccountStatus);
	}

	Account extractAccount(ResultSet rs) throws SQLException {
		int id = rs.getInt("user_id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String firstname = rs.getString("firstname");
		String lastname = rs.getString("lastname");
		String address = rs.getString("address");
		String usertype = rs.getString("usertype");
		return new Account(id, username, password, firstname, lastname, address, usertype);
	}

	Transaction extractTransaction(ResultSet rs) throws SQLException {
		int recordId = rs.getInt("record_id");
		int userId = rs.getInt("user_id");
		int accountId = rs.getInt("account_id");
		String transactionType = rs.getString("transaction_type");
		int transactionAmount = rs.getInt("transaction_amount");
		String time = rs.getString("time");
		Long timelong = Long.parseLong(time);
		java.util.Date time2=new java.util.Date((long)timelong);
		return new Transaction(recordId, userId, accountId, transactionType, transactionAmount, time2.toString());
	}

	@Override
	public int save(Account a) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO bank_users (user_id, username, password, firstname, lastname, address, usertype) "
					+ " VALUES (bank_users_id_seq.nextval ,?,?,?,?,?, 'user')";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, a.getUsername());
			ps.setString(2, a.getPassword());
			ps.setString(3, a.getFirstname());
			ps.setString(4, a.getLastname());
			ps.setString(5, a.getAddress());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int saveSubAccount(SubAccount sa) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO bank_accounts (account_id, user_id, account_type, account_balance, account_status) "
					+ " VALUES (bank_accounts_id_seq.nextval, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, sa.getUser_id());
			ps.setString(2, sa.getAccount_type());
			ps.setInt(3, sa.getAccount_balance());
			ps.setString(4, sa.getAccount_status());
			System.out.println("Success!\nYou now have a new " + sa.getAccount_type().toUpperCase() + " account.\n");
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Account> findAll() {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_users";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Account> account = new ArrayList<>();
			while (rs.next()) {
				account.add(extractAccount(rs));
			}
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<SubAccount> findAllSubAccount() {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_accounts";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<SubAccount> subAccount = new ArrayList<>();
			while (rs.next()) {
				subAccount.add(extractSubAccount(rs));
			}
			return subAccount;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	};

	public Account findByUsername(String username) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_users WHERE username = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractAccount(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Account findByUsernameAndPassword(String username, String password) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_users " + "WHERE username = ? AND password = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractAccount(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<SubAccount> findSubAccount(String username) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "select * from bank_users bu JOIN bank_accounts ba ON bu.user_id = ba.user_id WHERE username = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			List<SubAccount> account = new ArrayList<>();
			while (rs.next()) {
				account.add(extractSubAccount(rs));
			}
			if (account.size() == 0) {
				System.out.println("There are no accounts associated with this account.");
			}
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int findUserId(String username) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT user_id FROM bank_users WHERE username = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("user_id");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public List<Integer> findAccounts(int userId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			System.out.println("Attempting to Gather accounts for UserID: " + userId);
			String sql = "select * from bank_accounts WHERE user_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, Integer.toString(userId));
			ResultSet rs = ps.executeQuery();
			List<Integer> accountIds = new ArrayList<Integer>();
			while (rs.next()) {
				accountIds.add(rs.getInt("account_id"));
			}
			return accountIds;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public int getBalance(int accountId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT account_balance FROM bank_accounts WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, Integer.toString(accountId));
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("account_balance");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int withdrawMoney(int newBalance, int withdrawAmount, int accountId, int userId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			// Making Withdrawal
			String sql = "UPDATE bank_accounts SET account_balance = ? WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, Integer.toString(newBalance));
			ps.setString(2, Integer.toString(accountId));
			ps.executeUpdate();
			// Updating Records
			String sql2 = "INSERT INTO bank_records (record_id, user_id, account_id, transaction_amount, transaction_type, time)\n"
					+ "VALUES (BANK_RECORDS_ID_SEQ.nextval, ?, ?, ?, 'withdrawal', ?)";
			PreparedStatement ps2 = c.prepareStatement(sql2);
			ps2.setInt(1, userId);
			ps2.setInt(2, accountId);
			ps2.setInt(3, withdrawAmount);
			ps2.setString(4, Long.toString(System.currentTimeMillis()));
			return ps2.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}

	public int depositMoney(int newBalance, int depositAmount, int accountId, int userId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			// First Making Deposit
			String sql = "UPDATE bank_accounts SET account_balance = ? WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, Integer.toString(newBalance));
			ps.setString(2, Integer.toString(accountId));
			ps.executeUpdate();
			// Updating Records
			String sql2 = "INSERT INTO bank_records (record_id, user_id, account_id, transaction_amount, transaction_type, time)\n"
					+ "VALUES (BANK_RECORDS_ID_SEQ.nextval, ?, ?, ?, 'deposit', ?)";
			PreparedStatement ps2 = c.prepareStatement(sql2);
			ps2.setInt(1, userId);
			ps2.setInt(2, accountId);
			ps2.setInt(3, depositAmount);
			ps2.setString(4, Long.toString(System.currentTimeMillis()));
			return ps2.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<Transaction> userTransaction(int accountId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM bank_records where account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accountId);
			List<Transaction> transactions = new ArrayList<Transaction>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				transactions.add(extractTransaction(rs));
			}
			return transactions;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int deleteAccount(int accountId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			// First Making Deposit
			String sql = "UPDATE bank_accounts SET account_status = 'closed' WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accountId);
			ps.executeUpdate();
			// Updating Records
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public String getAccountStatus(int accountId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT account_status FROM bank_accounts WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString("account_status");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	};

}
