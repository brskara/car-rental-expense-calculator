package com.cognizant.carrental.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.carrental.model.Expense;
import com.cognizant.carrental.services.ExpenseCalculatorService;

@RestController
public class ExpenseController {
	private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);
	
	@Autowired
	private ExpenseCalculatorService expenseCalculatorService;
	

	@RequestMapping(value="/expense", method=RequestMethod.GET)
	public Expense calculateExpense(@RequestParam(value="vehicle") String vehicle,
			@RequestParam(value="fuel") String fuel,
			@RequestParam(value="hasAC", required = false, defaultValue="false") boolean hasAC,
			@RequestParam(value="route") List<String> route,
			@RequestParam(value="number-of-passengers", required = false, defaultValue="1") int numberOfPassengers){
		
		logger.info("Calling expense calculator service with parameters vehicle: {}, fuel type: {}, has AC: {}, route: {}, numberOfPassengers: {}", vehicle, fuel, hasAC, route, numberOfPassengers);
		Expense expense = expenseCalculatorService.calculate(vehicle.toUpperCase(), fuel.toUpperCase(), hasAC, route, numberOfPassengers);
		return expense;
	}
	
	/**
	 * This method catches @IllegalArgumentException caused by an incorrect parameter   
	 * and sends "400 Bad Request" Response instead of "500 Internal Server Error" 
	 * @param e
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	

}
