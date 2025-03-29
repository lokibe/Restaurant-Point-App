package org.makerminds.internship.java.restaurantpoint.utils;

import java.util.HashMap;
import java.util.Map;

import org.makerminds.internship.java.restaurantpoint.model.UserFeature;

public class UserFeatureLabelResolver {

	private static Map<UserFeature, String> userFeatureLabelMap;

	public static String getUserFeatureLabelText(UserFeature userFeature) {
		return getUserFeatureLabelMap().get(userFeature);
	}

	private static Map<UserFeature, String> getUserFeatureLabelMap() {
		if (userFeatureLabelMap == null) {
			userFeatureLabelMap = new HashMap<>();
			userFeatureLabelMap.put(UserFeature.RESTAURANTS, "Restaurant Manager");
			userFeatureLabelMap.put(UserFeature.MENUS, "Menu Manager");
			userFeatureLabelMap.put(UserFeature.ITEMS, "Menu Item Manager");
			userFeatureLabelMap.put(UserFeature.TABLES, "Table Manager");
		}
		return userFeatureLabelMap;
	}
}
