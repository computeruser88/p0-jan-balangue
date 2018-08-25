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
		
		System.out.println("Revature Bank");
		System.out.println("-------------\n");
		System.out.println("Would you like to login, register, or exit?");
		String option;
		do {
			option = sc.nextLine();
			switch (option) {
			case "login":
				System.out.println("Enter username: ");
				String userName = sc.nextLine();
				System.out.println("Enter password: ");
				String password = sc.nextLine();
				User user = new User(userName, password);
				if (user.validateIdentity())	{
					System.out.println("User authorized.");
					String transaction = "";
					while(transaction != "Logout") {
						transaction = transactionQuery(user);
					}
				} else {
					log.error("Invalid login credentials.");
				}
				break;
			case "register":
				System.out.println("Enter username: ");
				String newUserName = sc.nextLine();
				System.out.println("Enter password: ");
				String newPassword = sc.nextLine();
				register(newUserName, newPassword);
				break;
			case "exit":	
				break;
			default:
				log.error("Invalid request. Please try again.");
			}
			System.out.println("Would you like to login, register, or exit?");
		} while (option != "exit");
		System.out.println("Thank you for your business. Goodbye.");
		sc.close();
		System.exit(0);
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
		System.out.println("\nWhat kind of transaction do you want? Please type one of the following: ");
		System.out.println("\tView balance");
		System.out.println("\tCreate transaction");
		System.out.println("\tLogout");
		String transaction = sc.nextLine();
		switch (transaction) {
			case "View balance":
				String[] accountRecords = bfr.readLines("./User.txt");
				System.out.println("Account balance:" + accountRecords[2]);
				break;
			case "Create transaction":
				System.out.println("Enter transaction type: deposit or withdrawal?");
				String transactionType = sc.nextLine();
				System.out.println("Enter amount:");
				String amountStr = sc.nextLine();
				double transactionAmount;
				try {
					transactionAmount = Double.parseDouble(amountStr);
					if (transactionAmount < 0) {
						log.error("Error: cannot have a negative transaction amount.");
					} else {
						user.performTransaction(transactionType, transactionAmount);
					}
					
				} catch (Exception e) {
					log.error("Invalid transaction amount.");
				}
				break;
			case "Logout":
				System.out.println("You are now logged out.");
				return "Logout";
			default:
				log.error("Invalid transaction type.");
		}
		return transaction;
	}
}
