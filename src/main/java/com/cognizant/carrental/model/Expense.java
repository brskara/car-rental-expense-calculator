package com.cognizant.carrental.model;

import java.util.List;

public class Expense {
	private String vehicle;
	private String fuelType;
	private boolean hasAC;
	private List<String> route; 
	private int numberOfPassengers;
	private double totalCost;
	private String currency;
	
	public Expense(String vehicle, String fuelType, boolean hasAC,
			List<String> route, int numberOfPassengers, double cost) {
		this.vehicle = vehicle;
		this.fuelType = fuelType;
		this.hasAC = hasAC;
		this.route = route;
		this.numberOfPassengers = numberOfPassengers;
		this.totalCost = cost;
		this.currency = "RS";
	}
	
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public boolean isHasAC() {
		return hasAC;
	}
	public void setHasAC(boolean hasAC) {
		this.hasAC = hasAC;
	}
	public List<String> getRoute() {
		return route;
	}
	public void setRoute(List<String> route) {
		this.route = route;
	}
	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "Expense [vehicle=" + vehicle + ", fuelType=" + fuelType
				+ ", hasAC=" + hasAC + ", route=" + route
				+ ", numberOfPassengers=" + numberOfPassengers + ", totalCost="
				+ totalCost + "]";
	}

}
