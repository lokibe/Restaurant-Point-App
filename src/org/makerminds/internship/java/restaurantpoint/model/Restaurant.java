package org.makerminds.internship.java.restaurantpoint.model;

import java.util.List;

public class Restaurant {

	private String name;
	private String address;
	private List<Menu> menuList;
	private List<Table> tableList;

	public Restaurant(String name, String addres) {
		setName(name);
		setAddress(addres);
	}

	public Restaurant(String name, String address, List<Menu> menuList, List<Table> tableList) {
		this.name = name;
		this.address = address;
		this.menuList = menuList;
		this.tableList = tableList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	public String toString() {
		return name;
	}
}