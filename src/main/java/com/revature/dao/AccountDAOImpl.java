package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.bank.Account;
import com.revature.util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	private static Logger log = Logger.getRootLogger();
	
	public List<Account> getAccounts() {
		
		List<Account> accountList = new ArrayList<>();
		
		String sql = "SELECT * FROM ACCOUNT";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				Account a = new Account();
				
				accountList.add(a);
			}
		} catch (IOException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		return accountList;
	}

	@Override
	public Account getAccountById(int id) {
		return null;
	}

	@Override
	public int createAccount(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAccount(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAccountById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
