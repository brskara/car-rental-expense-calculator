package com.cognizant.carrental.services;

import java.util.List;

import com.cognizant.carrental.model.Expense;

public interface ExpenseCalculatorService {
	 Expense calculate(String vehicle, String fuel, boolean hasAC, List<String> route, int numberOfPassengers);
}
