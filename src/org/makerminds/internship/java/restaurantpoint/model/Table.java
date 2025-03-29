package org.makerminds.internship.java.restaurantpoint.model;

public class Table {

	private int id;
	private int seats;

	public Table(int id, int numberOfSeats) {
		this.id = id;
		this.seats = numberOfSeats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfSeats() {
		return seats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.seats = numberOfSeats;
	}
}