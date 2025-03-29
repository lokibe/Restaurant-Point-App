package org.makerminds.internship.java.restaurantpoint.controller;

import java.util.Iterator;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.model.Item;
import org.makerminds.internship.java.restaurantpoint.model.ItemCategory;
import org.makerminds.internship.java.restaurantpoint.model.Menu;

public class MenuItemManagementController {

	private static Item createItem(String[] itemDataArray) {
		Item item = new Item(Integer.valueOf(itemDataArray[0]), itemDataArray[1], Double.valueOf(itemDataArray[2]), getItemCategory(itemDataArray[3]));
		return item;
	}
	
	//create a method that returns Enum as String
	private static ItemCategory getItemCategory(String string) {
		if(string.equals("MEAL")) {
			return ItemCategory.MEAL;
		}else {
			return ItemCategory.DRINK;
		}
	}
	
	public static void addItem(String[] itemDataArray, Menu selectedMenu) {
		Item item = createItem(itemDataArray);
		selectedMenu.getItemList().add(item);
	}
	
	public static void deleteItem(String[] itemDataArray, Menu selectedMenu) {
		Item itemToRemove = createItem(itemDataArray);
		List<Item> itemList = selectedMenu.getItemList();
		Iterator<Item> iterator = itemList.iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			if(item.getId() == itemToRemove.getId()) {
				iterator.remove();
			}
		}
	}
	
	public static void updateItem(int oldItemId, String[] newItemData, Menu selectedMenu) {
		List<Item> itemList = selectedMenu.getItemList();
		for(Item item : itemList) {
			if(item.getId() == oldItemId) {
				item.setId(Integer.valueOf(newItemData[0]));
				item.setName(newItemData[1]);
				item.setPrice(Double.valueOf(newItemData[2]));
				item.setItemCategory(getItemCategory(newItemData[3]));
			}
		}
		selectedMenu.setItemList(itemList);
	}
}
