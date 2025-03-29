package org.makerminds.internship.java.restaurantpoint.controller;

import java.util.Iterator;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.model.Menu;
import org.makerminds.internship.java.restaurantpoint.model.Restaurant;

public class MenuManagementController {

	private static Menu createMenu(String[] menuData) {
		String id = menuData[0];
		String name = menuData[1];
		
		Menu menu = new Menu(Integer.valueOf(id), name);
		return menu;
	}

	public static void addMenu(String[] menuData, Restaurant selectedRestaurant) {
		Menu menu = createMenu(menuData);
		selectedRestaurant.getMenuList().add(menu);
	}

	public static void deleteMenu(String[] menuData, Restaurant selectedRestaurant) {
		Menu menuToRemove = createMenu(menuData);
		List<Menu> menuList = selectedRestaurant.getMenuList();
		Iterator<Menu> menuIterator = menuList.iterator();
		while (menuIterator.hasNext()) {
			Menu menu = menuIterator.next();
			if (menuToRemove.getName().equals(menu.getName())) {
				menuIterator.remove();
			}
		}
		
	}

	public static void updateMenu(String[] oldMenuData, String[] newMenuData, Restaurant selectedRestaurant) {
		List<Menu> menuList = selectedRestaurant.getMenuList();
		for(Menu menu : menuList) {
			if(menu.getId() == (Integer.valueOf(oldMenuData[0]))) {
				menu.setId(Integer.valueOf(newMenuData[0]));
				menu.setName(newMenuData[1]);
			}
		}
		selectedRestaurant.setMenuList(menuList);
	}
}
