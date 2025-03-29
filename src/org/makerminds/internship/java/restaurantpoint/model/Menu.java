package org.makerminds.internship.java.restaurantpoint.model;

import java.util.List;

public class Menu {

	private int id;
	private String name;
	private List<Item> itemList;

	public Menu(int id, String name) {
		setId(id);
		setName(name);
	}
	
	public Menu(int id, String name, List<Item> itemList) {
		setId(id);
		setName(name);
		setItemList(itemList);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public String toString() {
		return name;
	}
}