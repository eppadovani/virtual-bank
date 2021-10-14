package com.example.virtualbank;

public class SpecialAccount extends Account {
	private double limit;

	public SpecialAccount(int accountId, double balance) {
		super(accountId, balance);
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	@Override
	public boolean withdraw(double amount) {
		if(this.getBalance() >= amount) {
			this.setBalance(this.getBalance() - amount);
			return true;
		} else if(this.getBalance() < amount && this.limit + this.getBalance() >= amount) {
			this.setBalance(this.getBalance() - amount);
			this.setLimit(this.getLimit() + this.getBalance());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Special Account " + this.getAccountId();
	}
}
