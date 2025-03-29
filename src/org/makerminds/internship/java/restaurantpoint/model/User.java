package org.makerminds.internship.java.restaurantpoint.model;

public class User {

	private String userName;
	private String userPassword;
	private UserRole userRole;

	public User(String userName, String userPassword, UserRole userRole) {
		this.setUserName(userName);
		this.setUserPassword(userPassword);
		this.setUserRole(userRole);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}