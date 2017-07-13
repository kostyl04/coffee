package by.kostyl.coffee.model;

import java.util.List;

import by.kostyl.coffee.entity.CoffeeType;

public class OrderDetails {
	private List<CoffeeType> coffeeList;
	private String  fullName="";
	private String address="";
	private double summWithoutDel;
	private double summWithDelivery;
	private double deliveryPrice;
	

	public double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public double getSummWithoutDel() {
		return summWithoutDel;
	}

	public void setSummWithoutDel(double summWithoutDel) {
		this.summWithoutDel = summWithoutDel;
	}

	public double getSummWithDelivery() {
		return summWithDelivery;
	}

	public void setSummWithDelivery(double summWithDelivery) {
		this.summWithDelivery = summWithDelivery;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CoffeeType> getCoffeeList() {
		return coffeeList;
	}

	public void setCoffeeList(List<CoffeeType> coffeeList) {
		this.coffeeList = coffeeList;
	}
	
}
