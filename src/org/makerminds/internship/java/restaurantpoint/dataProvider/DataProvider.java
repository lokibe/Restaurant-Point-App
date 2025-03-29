package org.makerminds.internship.java.restaurantpoint.dataProvider;

import java.util.ArrayList;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.model.Item;
import org.makerminds.internship.java.restaurantpoint.model.ItemCategory;
import org.makerminds.internship.java.restaurantpoint.model.Menu;
import org.makerminds.internship.java.restaurantpoint.model.Restaurant;
import org.makerminds.internship.java.restaurantpoint.model.Table;

public class DataProvider {

	private List<Restaurant> restaurantList;

	public DataProvider() {
		createRestaurantList();
	}

	private List<Item> createItemList() {

		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(100, "Hamburger", 2.5, ItemCategory.MEAL));
		itemList.add(new Item(101, "HotDog", 2.5, ItemCategory.MEAL));
		itemList.add(new Item(102, "Pizza", 5.0, ItemCategory.MEAL));

		return itemList;
	}

	private List<Item> createItemList2() {

		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(100, "Hamburger2", 2.5, ItemCategory.MEAL));
		itemList.add(new Item(101, "HotDog2", 2.5, ItemCategory.MEAL));
		itemList.add(new Item(102, "Pizza2", 5.0, ItemCategory.MEAL));

		return itemList;
	}

	private List<Item> createItemList3() {

		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(100, "Hamburger3", 2.5, ItemCategory.MEAL));
		itemList.add(new Item(101, "HotDog3", 2.5, ItemCategory.MEAL));
		itemList.add(new Item(102, "Pizza3", 5.0, ItemCategory.MEAL));

		return itemList;
	}

	private List<Menu> createMenuList() {
		List<Menu> menuList = new ArrayList<>();
		menuList.add(new Menu(1, "Burger Menu", createItemList()));
		menuList.add(new Menu(2, "HotDog Menu", createItemList2()));
		menuList.add(new Menu(3, "Pizza Menu", createItemList3()));
		return menuList;
	}

	private List<Menu> createMenuList2() {
		List<Menu> menuList = new ArrayList<>();
		menuList.add(new Menu(1, "Burger Menu2", createItemList()));
		menuList.add(new Menu(2, "HotDog Menu2", createItemList2()));
		menuList.add(new Menu(3, "Pizza Menu2", createItemList3()));
		return menuList;
	}

	private List<Menu> createMenuList3() {
		List<Menu> menuList = new ArrayList<>();
		menuList.add(new Menu(1, "Burger Menu3", createItemList()));
		menuList.add(new Menu(2, "HotDog Menu3", createItemList2()));
		menuList.add(new Menu(3, "Pizza Menu3", createItemList3()));
		return menuList;
	}

	private List<Table> createTableList() {
		List<Table> tableList = new ArrayList<>();
		tableList.add(new Table(11, 6));
		tableList.add(new Table(21, 8));
		tableList.add(new Table(31, 10));
		tableList.add(new Table(41, 12));
		return tableList;
	}
	
	private List<Table> createTableList2() {
		List<Table> tableList = new ArrayList<>();
		tableList.add(new Table(12, 6));
		tableList.add(new Table(22, 8));
		tableList.add(new Table(32, 10));
		tableList.add(new Table(42, 12));
		return tableList;
	}
	
	private List<Table> createTableList3() {
		List<Table> tableList = new ArrayList<>();
		tableList.add(new Table(13, 6));
		tableList.add(new Table(23, 8));
		tableList.add(new Table(33, 10));
		tableList.add(new Table(43, 12));
		return tableList;
	}

	private Restaurant createRestaurant(String name, String address, List<Menu> menuList, List<Table> tableList) {
		return new Restaurant(name, address, menuList, tableList);
	}

	private List<Restaurant> createRestaurantList() {
		restaurantList = new ArrayList<>();
		restaurantList.add(createRestaurant("Restaurant 1", "Address 1", createMenuList(), createTableList()));
		restaurantList.add(createRestaurant("Restaurant 2", "Address 2", createMenuList2(), createTableList2()));
		restaurantList.add(createRestaurant("Restaurant 3", "Address 3", createMenuList3(), createTableList3()));

		return restaurantList;
	}

	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}
}