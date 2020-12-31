package com.revature.models;

public class MenuItem {
	
	public String name;
	public double price;
	
	public MenuItem() {
		super();
	}

	public MenuItem(String name, double price) {
		super();
		this.name = name;
		this.price = price;
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

	@Override
	public String toString() {
		return "MenuItem [name=" + name + ", price=" + price + "]";
	}
}
