package com.example.virtualbank;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Manager extends User {
	private ArrayList<Customer> customerList = new ArrayList<Customer>();

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public Manager(String username, String password) {
		super("manager", username, password);
	}

}
