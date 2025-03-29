package org.makerminds.internship.java.restaurantpoint.utils;

import java.util.Arrays;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.model.UserFeature;
import org.makerminds.internship.java.restaurantpoint.model.UserRole;

public class AuthorizationService {

	public List<UserFeature> getUserFeatureByUserRole(UserRole userRole) {

		switch (userRole) {
		case ADMIN: {
			return Arrays.asList(UserFeature.RESTAURANTS, UserFeature.MENUS, UserFeature.ITEMS, UserFeature.TABLES);
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + userRole);
		}
	}
}
