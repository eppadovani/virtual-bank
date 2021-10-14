package com.example.virtualbank;

public class SavingsAccount extends Account {
	private double percentage;

	public SavingsAccount(int accountId, double balance) {
		super(accountId, balance);
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public boolean withdraw(double amount) {
		if(this.getBalance() >= amount) {
			this.setBalance(this.getBalance() - amount);
			return true;
		}

		return false;
	}

	public void investmentGains() {

	}

	@Override
	public String toString() {
		return "Savings Account " + this.getAccountId();
	}
}
