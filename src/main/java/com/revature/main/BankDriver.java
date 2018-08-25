package com.revature.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bank.User;
import com.revature.io.BankFileReader;

public class BankDriver {
	
	private static Scanner sc = new Scanner(System.in);
	private static BankFileReader bfr = new BankFileReader();
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
				String userName = sc.nextLine();
				log.info("Enter password: ");
				String password = sc.nextLine();
				User user = new User(userName, password);
				if (user.validateIdentity())	{
					log.info("User authorized.");
					String transaction = "";
					while(transaction != "Logout") {
						transaction = transactionQuery(user);
					}
				} else {
					log.error("Invalid login credentials.");
				}
				break;
			case "register":
				log.info("Enter username: ");
				String newUserName = sc.nextLine();
				log.info("Enter password: ");
				String newPassword = sc.nextLine();
				register(newUserName, newPassword);
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
	
	public static void register (String userName, String password) {
		String[] checkIfAlreadyRegistered = bfr.readLines("./User.txt");
		if (checkIfAlreadyRegistered[0] == "") {
			User newUser = new User(userName, password);
			String transaction = "";
			while(transaction != "Logout") {
				transaction = transactionQuery(newUser);
			}
		} else {
			log.error("A user has already been registered. Login instead.");
		}
	}
	
	public static String transactionQuery(User user) {
		log.info("\nWhat kind of transaction do you want? Please type one of the following: ");
		log.info("\tView balance");
		log.info("\tCreate transaction");
		log.info("\tLogout");
		String transaction = sc.nextLine();
		switch (transaction) {
			case "View balance":
				String[] accountRecords = bfr.readLines("./User.txt");
				log.info("Account balance:" + accountRecords[2]);
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
						user.performTransaction(transactionType, transactionAmount);
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
