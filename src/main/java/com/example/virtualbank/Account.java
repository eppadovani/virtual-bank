package com.example.virtualbank;

import java.util.ArrayList;

public abstract class Account {
	public ArrayList<String> bankStatement = new ArrayList<>();

	private int accountId;
	private double balance;

	public Account(int accountId, double balance) {
		this.accountId = accountId;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void deposit(double amount) {
		this.balance = amount;
	}

	public abstract boolean withdraw(double amount);

}
