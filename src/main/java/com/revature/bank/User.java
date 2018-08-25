package com.revature.bank;

import java.util.ArrayList;
import com.revature.io.BankFileReader;
import com.revature.io.BankFileWriter;

public class User {
 	private String userName;
	private String password;
	private String balanceStr;
	private ArrayList<String> profile = new ArrayList<>();
	protected static final String path = "./User.txt";
	private String[] checkIfUserExists;
	private BankFileWriter bfw = new BankFileWriter();
	private BankFileReader bfr = new BankFileReader();
	
	public User() {
		super();
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.balanceStr = "0.0";
		this.profile.clear();
		this.profile.add(userName);
		this.profile.add(password);
		this.profile.add(balanceStr);
		this.checkIfUserExists = bfr.readLines(path);
		if (this.checkIfUserExists[0] == "") {
			this.writeUser();
		}
	}
	
	public void writeUser() {
		bfw.writeFile(this.profile, path);
	}
	public boolean validateIdentity() {
		String[] authorizedUser = bfr.readLines(path);
		return authorizedUser[0].equals(this.userName) && authorizedUser[1].equals(this.password);
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBalanceStr() {
		return this.balanceStr;
	}
	
	public void setBalanceStr(String balanceStr) {
		this.balanceStr = balanceStr;
		this.profile.set(2, balanceStr);
		bfw.writeFile(this.profile, path);
	}
 	public void performTransaction(String transactionType, double transactionAmount) {
		double balance = Double.parseDouble(this.balanceStr);
		switch (transactionType) {
		case "deposit":
			balance += transactionAmount;		
			balanceStr = Double.toString(balance);
 			System.out.println("Deposit completed. Your new balance is " + balanceStr);
 			this.profile.set(2, balanceStr);
 			bfw.writeFile(this.profile, path);
			break;
		case "withdrawal":
			if (balance >= transactionAmount) {
				balance -= transactionAmount;
				balanceStr = Double.toString(balance);		
	 			System.out.println("Withdrawal completed. Your new balance is " + balanceStr);
	 			this.profile.set(2, balanceStr);
	 			bfw.writeFile(this.profile, path);				
			} else {
				System.out.println("Insufficient funds for withdrawal. No changes made.");
			}
			break;
		default:
			System.out.println("Invalid transaction type.");
		}
	}
 	@Override
	public String toString() {
		return "userName=" + userName
				+ ", password=" + password + "]";
	}
}