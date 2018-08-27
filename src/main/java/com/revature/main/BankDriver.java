package com.revature.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bank.Customer;
//import com.revature.io.BankFileReader;

public class BankDriver {
	
	private static Scanner sc = new Scanner(System.in);
//	private static BankFileReader bfr = new BankFileReader();
	private static Logger log = Logger.getRootLogger();
	
	
	public static void main(String[] args) {
		
		log.info("Revature Bank");
		log.info("-------------\n");
		log.info("Would you like to login, register, or exit?");
		String option = null;
		do {
			option = sc.nextLine();
			switch (option) {
			case "login":
				log.info("Enter username: ");
				String customerName = sc.nextLine();
				log.info("Enter password: ");
				String password = sc.nextLine();
				Customer customer = new Customer(customerName, password);
				if (customer.validateIdentity())	{
					log.info("User authorized.");
					String transaction = "";
					while(transaction != "Logout") {
						transaction = transactionQuery(customer);
					}
				} else {
					log.error("Invalid login credentials.");
				}
				break;
			case "register":
				log.info("Enter username: ");
				String newCustomerName = sc.nextLine();
				log.info("Enter password: ");
				String newPassword = sc.nextLine();
				register(newCustomerName, newPassword);
				break;
			case "exit":	
				log.info("Thank you for your business. Goodbye.");
				sc.close();
				System.exit(0);
				break;
			default:
				log.error("Invalid request. Please try again.");
			}
			log.info("Would you like to login, register, or exit?");
		} while (option != "exit");
	}
	
	public static void register (String customerName, String password) {
//		String[] checkIfAlreadyRegistered = bfr.readLines("./User.txt");
//		if (checkIfAlreadyRegistered[0] == "") {
//			Customer newCustomer = new Customer(customerName, password);
//			String transaction = "";
//			while(transaction != "Logout") {
//				transaction = transactionQuery(newCustomer);
//			}
//		} else {
//			log.error("A user has already been registered. Login instead.");
//		}
	}
	
	public static String transactionQuery(Customer customer) {
		log.info("\nWhat kind of transaction do you want? Please type one of the following: ");
		log.info("\tView balance");
		log.info("\tCreate transaction");
		log.info("\tLogout");
		String transaction = sc.nextLine();
		switch (transaction) {
			case "View balance":
//				String[] accountRecords = bfr.readLines("./User.txt");
//				log.info("Account balance:" + accountRecords[2]);
				break;
			case "Create transaction":
				log.info("Enter transaction type: deposit or withdrawal?");
				String transactionType = sc.nextLine();
				log.info("Enter amount:");
				String amountStr = sc.nextLine();
				double transactionAmount;
				try {
					transactionAmount = Double.parseDouble(amountStr);
					if (transactionAmount <= 0) {
						log.error("Error: cannot have a negative or zero transaction amount.");
					} else {
						customer.performTransaction(transactionType, transactionAmount);
					}
					
				} catch (Exception e) {
					log.error("Invalid transaction amount.");
				}
				break;
			case "Logout":
				log.info("You are now logged out.");
				return "Logout";
			default:
				log.error("Invalid transaction type.");
		}
		return transaction;
	}
}
