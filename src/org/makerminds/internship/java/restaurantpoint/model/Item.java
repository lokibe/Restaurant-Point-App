package org.makerminds.internship.java.restaurantpoint.model;

public class Item {

	private int id;
	private String name;
	private double price;
	private ItemCategory itemCategory;

	public Item(int id, String name, double price, ItemCategory itemCategory) {
		setId(id);
		setName(name);
		setPrice(price);
		setItemCategory(itemCategory);
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}
}