package org.makerminds.internship.java.restaurantpoint.model;

public enum UserFeature {

	WELCOME,
	// user role = Admin
	// List<String> featuresForAdmin = Arrays.asList("Restaurant Manager", "Menu
	// Manager", "Menu Item Manager", "Table Manager", "Sign-out");
	RESTAURANTS, MENUS, ITEMS, TABLES,
	// user role = Employee
}
