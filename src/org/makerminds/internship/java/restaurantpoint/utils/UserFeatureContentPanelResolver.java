package org.makerminds.internship.java.restaurantpoint.utils;

import java.util.HashMap;
import java.util.Map;

import org.makerminds.internship.java.restaurantpoint.model.UserFeature;
import org.makerminds.internship.java.restaurantpoint.view.IView;
import org.makerminds.internship.java.restaurantpoint.view.admin.MenuItemManager;
import org.makerminds.internship.java.restaurantpoint.view.admin.MenuManager;
import org.makerminds.internship.java.restaurantpoint.view.admin.RestaurantManager;
import org.makerminds.internship.java.restaurantpoint.view.admin.TableManager;

public class UserFeatureContentPanelResolver {

	private static Map<UserFeature, IView> userFeatureIViewMap;

	public UserFeatureContentPanelResolver() {
		createUserFeatureIViewMap();
	}
	
	private static Map<UserFeature, IView> createUserFeatureIViewMap() {
		if (userFeatureIViewMap == null) {
			userFeatureIViewMap = new HashMap<>();
			userFeatureIViewMap.put(UserFeature.RESTAURANTS, new RestaurantManager());
			userFeatureIViewMap.put(UserFeature.MENUS, new MenuManager());
			userFeatureIViewMap.put(UserFeature.ITEMS, new MenuItemManager());
			userFeatureIViewMap.put(UserFeature.TABLES, new TableManager());
		}
		return userFeatureIViewMap;
	}
	
	public IView getUserFeatureIView(UserFeature userFeature) {
		return userFeatureIViewMap.get(userFeature);
	}
}
