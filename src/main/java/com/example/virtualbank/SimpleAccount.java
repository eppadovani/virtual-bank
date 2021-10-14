package com.example.virtualbank;

public class SimpleAccount extends Account {

	public SimpleAccount(int accountId, double balance) {
		super(accountId, balance);
	}

	@Override
	public boolean withdraw(double amount) {
		if(this.getBalance() >= amount) {
			this.setBalance(this.getBalance() - amount);
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Simple Account " + this.getAccountId();
	}
}
