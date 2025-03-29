package org.makerminds.internship.java.restaurantpoint.controller;

import org.makerminds.internship.java.restaurantpoint.dataProvider.UserDataProvider;
import org.makerminds.internship.java.restaurantpoint.model.User;

public class LoginController {

	private static final LoginController INSTANCE = new LoginController();
	private UserDataProvider userDataProvider = new UserDataProvider();
	private User loggedInUser = null;

	private LoginController() {

	}

	public static LoginController getInstance() {
		return INSTANCE;
	}

	public void loginUser(String username, String password) {
		for (User user : userDataProvider.getUserList()) {
			if (user.getUserName().equals(username) && user.getUserPassword().equals(password)) {
				loggedInUser = user;
			}
		}
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public boolean isStringNullOrBlank(String input) {
		return input.isBlank() || input == null;
	}
}