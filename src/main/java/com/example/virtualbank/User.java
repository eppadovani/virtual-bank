package com.example.virtualbank;

public abstract class User {
	private String type;
	private String username;
	private String password;

	public User(String type, String username, String password) {
		this.type = type;
		this.username = username;
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public boolean auth(String oldPassword, String newPassword) {
		boolean result = oldPassword.equals(password);
		if(result) {
			password = newPassword;
		}

		return result;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
