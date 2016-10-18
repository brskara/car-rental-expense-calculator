package com.cognizant.carrental.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.carrental.model.Expense;
import com.cognizant.carrental.services.discount.DiscountCalculatorService;
import com.cognizant.carrental.services.distance.DistanceCalculatorService;
import com.cognizant.carrental.services.vehicle.RateCalculatorService;
import com.cognizant.carrental.util.Precision;

@Service
public class ExpenseCalculatorServiceImpl implements ExpenseCalculatorService{
	
	@Autowired
	private DistanceCalculatorService distanceCalculatorService;
	
	@Autowired
	private RateCalculatorService rateCalculatorService;
	
	@Autowired
	private DiscountCalculatorService discountCalculatorService;

	@Override
	public Expense calculate(String vehicle, String fuelType, boolean hasAC, List<String> route, int numberOfPassengers) {
		double distance = distanceCalculatorService.calculateDistance(route);
		double ratePerKm = rateCalculatorService.calculateRate(vehicle, fuelType, hasAC, numberOfPassengers);
		double discountRate = discountCalculatorService.calculateDiscount(vehicle);
		double cost = distance * ratePerKm * (1 - discountRate);
		cost = Precision.round(cost, 2);
		return new Expense(vehicle, fuelType, hasAC, route, numberOfPassengers, cost);
	}


}
