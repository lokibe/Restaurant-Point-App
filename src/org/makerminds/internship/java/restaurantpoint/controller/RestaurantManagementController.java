package org.makerminds.internship.java.restaurantpoint.controller;

import java.util.Iterator;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.dataProvider.DataProvider;
import org.makerminds.internship.java.restaurantpoint.model.Restaurant;

public class RestaurantManagementController {

	private static Restaurant createRestaurant(String[] restaurantData) {
		
		Restaurant restaurant = new Restaurant(restaurantData[0], restaurantData[1]);
		return restaurant;
	}

	public static void addRestaurant(String[] restaurantData, List<Restaurant> restaurantList) {
		Restaurant restaurant = createRestaurant(restaurantData);
		restaurantList.add(restaurant);
	}

	public static void deleteRestaurant(String[] restaurantData, List<Restaurant> restaurantList) {
		Restaurant restaurantToRemove = createRestaurant(restaurantData);
		Iterator<Restaurant> iterator = restaurantList.iterator();
		while (iterator.hasNext()) {
			Restaurant restaurant = iterator.next();
			if (restaurantToRemove.getName().equals(restaurant.getName())) {
				iterator.remove();
			}
		}
		/*
		 * if(menuList.contains(menuToRemove.getId())) { menuList.remove(menuToRemove);
		 * }
		 */
	}

	public static void updateRestaurant(String[] oldRestaurantData, String[] newRestaurantData, Restaurant selectedRestaurant) {
		DataProvider dataProvider = new DataProvider();
		List<Restaurant> restaurantList = dataProvider.getRestaurantList();
		for(Restaurant restaurant : restaurantList) {
			if(restaurant.getName().equals(oldRestaurantData[0])) {
				restaurant.setName(newRestaurantData[0]);
				restaurant.setAddress(newRestaurantData[1]);
			}
		}
	}
}