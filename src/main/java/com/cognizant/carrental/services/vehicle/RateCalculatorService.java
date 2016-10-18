package com.cognizant.carrental.services.vehicle;


public interface RateCalculatorService {
	 double calculateRate(String vehicle, String fuelType, boolean hasAC, int numberOfPassengers);
}
