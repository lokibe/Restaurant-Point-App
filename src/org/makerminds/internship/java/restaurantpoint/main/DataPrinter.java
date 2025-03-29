package org.makerminds.internship.java.restaurantpoint.main;

import java.util.List;

import org.makerminds.internship.java.restaurantpoint.dataProvider.DataProvider;
import org.makerminds.internship.java.restaurantpoint.model.Menu;
import org.makerminds.internship.java.restaurantpoint.model.Restaurant;

public class DataPrinter {

	public static void main(String[] args) {

		DataProvider dataProvider = new DataProvider();
		List<Restaurant> restaurantList = dataProvider.getRestaurantList();
		for (Restaurant restaurant : restaurantList) {
			System.out.println("-------------------------------------------------");
			System.out.println("Restaurants name: " + restaurant.getName());

			System.out.println("-------------------------------------------------");
			List<Menu> menuList = restaurant.getMenuList();
			System.out.println("Menus of restaurant: " + menuList);
		}
	}
}
