package org.makerminds.internship.java.restaurantpoint.model;

public class Client {

	private String name;
	private String phoneNr;

	// Parameterized constructor
	public Client(String name, String phoneNr) {
		this.name = name;
		this.phoneNr = phoneNr;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

}
