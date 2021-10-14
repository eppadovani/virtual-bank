package com.example.virtualbank;


//customer class
import java.util.ArrayList;

public class Customer extends User {
	private int specialAccounts, regularAccounts, savingsAccounts;

	public int getSpecialAccounts() {
		return specialAccounts;
	}

	public void setSpecialAccounts(int specialAccounts) {
		this.specialAccounts += specialAccounts;
	}

	public int getRegularAccounts() {
		return regularAccounts;
	}

	public void setRegularAccounts(int regularAccounts) {
		this.regularAccounts += regularAccounts;
	}

	public int getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccounts(int savingsAccounts) {
		this.savingsAccounts += savingsAccounts;
	}

	private ArrayList<Account> accounts = new ArrayList<>();

	private ArrayList<SpecialAccount> specialAccountsList = new ArrayList<>();

	private ArrayList<SavingsAccount> savingsAccountList = new ArrayList<>();

	public Customer(String username, String password) {
		super("customer", username, password);
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public ArrayList<SpecialAccount> getSpecialAccountsList() { return specialAccountsList; }

	public ArrayList<SavingsAccount> getSavingsAccountList() { return savingsAccountList; }

	@Override
	public String toString() {
		return this.getUsername();
	}
}
