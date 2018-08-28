package com.revature.bank;

import java.util.ArrayList;

import org.apache.log4j.Logger;

//import com.revature.io.BankFileReader;
//import com.revature.io.BankFileWriter;

public class Customer {
	private int customerId;
 	private String customerName;
	private String password;
	private String balanceStr;
	private ArrayList<String> profile = new ArrayList<>();
	protected static final String PATH = "./User.txt";
	private String[] checkIfCustomerExists;
//	private BankFileWriter bfw = new BankFileWriter();
//	private BankFileReader bfr = new BankFileReader();
	private static Logger log = Logger.getRootLogger();
	
	public Customer() {
		super();
	}
	
	public Customer(String customerName, String password) {
		super();
		this.customerName = customerName;
		this.password = password;
		this.balanceStr = "0.0";
		this.profile.clear();
		this.profile.add(customerName);
		this.profile.add(password);
		this.profile.add(balanceStr);
//		this.checkIfCustomerExists = bfr.readLines(PATH);
		if (this.checkIfCustomerExists[0] == "") {
			this.writeCustomer();
		}
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerID(int id) {
		this.customerId = id;
	}
	
	public void writeCustomer() {
//		bfw.writeFile(this.profile, PATH);
	}
	public boolean validateIdentity() {
//		String[] authorizedCustomer = bfr.readLines(PATH);
//		return authorizedCustomer[0].equals(this.customerName) && authorizedCustomer[1].equals(this.password);
		return false;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
//		bfw.writeFile(this.profile, PATH);
	}
 	public void performTransaction(String transactionType, double transactionAmount) {
		double balance = Double.parseDouble(this.balanceStr);
		switch (transactionType) {
		case "deposit":
			balance += transactionAmount;		
			balanceStr = Double.toString(balance);
 			log.info("Deposit completed. Your new balance is " + balanceStr);
 			this.profile.set(2, balanceStr);
// 			bfw.writeFile(this.profile, PATH);
			break;
		case "withdrawal":
			if (balance >= transactionAmount) {
				balance -= transactionAmount;
				balanceStr = Double.toString(balance);		
	 			log.info("Withdrawal completed. Your new balance is " + balanceStr);
	 			this.profile.set(2, balanceStr);
//	 			bfw.writeFile(this.profile, PATH);				
			} else {
				log.info("Insufficient funds for withdrawal. No changes made.");
			}
			break;
		default:
			log.info("Invalid transaction type.");
		}
	}
 	@Override
	public String toString() {
		return "customerName=" + customerName
				+ ", password=" + password + "]";
	}
}