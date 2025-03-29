package org.makerminds.internship.java.restaurantpoint.dataProvider;

import java.util.ArrayList;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.model.User;
import org.makerminds.internship.java.restaurantpoint.model.UserRole;

public class UserDataProvider {

	List<User> userList = new ArrayList<>();

	public UserDataProvider() {
		createUserList();
	}

	private void createUserList() {
		User user1 = new User("admin", "test123", UserRole.ADMIN);

		userList.add(user1);

	}

	public List<User> getUserList() {
		return userList;
	}
}
