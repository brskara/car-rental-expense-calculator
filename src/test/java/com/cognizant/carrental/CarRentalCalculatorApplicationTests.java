package com.cognizant.carrental;


import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.carrental.model.Expense;
import com.cognizant.carrental.services.discount.DiscountCalculatorService;
import com.cognizant.carrental.services.distance.DistanceCalculatorService;
import com.cognizant.carrental.services.vehicle.RateCalculatorService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarRentalCalculatorApplicationTests {

	private static final String EXPENSE_RESOURCE = "/expense"; 
			
	@Autowired
	DiscountCalculatorService discountCalculatorService;
	
	@Autowired
	RateCalculatorService rateCalculatorService;
	
	@Autowired
	DistanceCalculatorService distanceCalculatorService;

	
	@Test
	public void getExpense_shouldReturnOK(){
		double expectedCost = 46305d;
		NumberMatcher matcher = new NumberMatcher(expectedCost);
		given()
			.queryParam("vehicle","bus")
			.queryParam("fuel","diesel")
			.queryParam("hasAC","true")
			.queryParam("route","pune,mumbai,delhi")
			.queryParam("number-of-passengers","50").
		when()
			.get(EXPENSE_RESOURCE).
		then()
			.statusCode(200)
			.body("vehicle", Matchers.is("BUS"))
			.body("fuelType", Matchers.is("DIESEL"))
			.body("hasAC",Matchers.is(true))
			.body("route", Matchers.hasItems("pune","mumbai","delhi"))
			.body("numberOfPassengers", Matchers.is(50))
			.body("currency",Matchers.is("RS"))
			.body("totalCost", matcher);
	}
	
	@Test
	public void getExpense_shouldReturnBadRequest_whenMissingParam(){
		given()
			.queryParam("fuel","diesel")
			.queryParam("hasAC","true")
			.queryParam("route","pune,mumbai,delhi")
			.queryParam("number-of-passengers","50").
		when()
			.get(EXPENSE_RESOURCE).
		then()
			.statusCode(400);
	}
	
	@Test
	public void getExpense_shouldReturnBadRequest_whenIllegalArgument(){
		given()
			.queryParam("vehicle","horsecar")
			.queryParam("fuel","diesel")
			.queryParam("hasAC","true")
			.queryParam("route","pune,mumbai,delhi")
			.queryParam("number-of-passengers","50").
		when()
			.get(EXPENSE_RESOURCE).
		then()
			.statusCode(400);
	}
	
	@Test
	public void calculateDiscount_forValidCase() {
		double rate = discountCalculatorService.calculateDiscount("BUS");
		Assert.assertNotNull(rate);
		Assert.assertEquals(0.02, rate, 0);
	}
	
	@Test
	public void calculateDiscount_forInvalidCase() {
		double rate = discountCalculatorService.calculateDiscount("CAR");
		Assert.assertEquals(0, rate, 0);
	}
	
	@Test
	public void calculateRates_standartUsage() {
		double rate = rateCalculatorService.calculateRate("CAR", "PATROL", false, 1);
		Assert.assertEquals(15, rate, 0);
	}
	
	@Test
	public void calculateRate_dieselUsage() {
		double rate = rateCalculatorService.calculateRate("CAR", "DIESEL", false, 1);
		Assert.assertEquals(14, rate, 0);
	}
	
	
	@Test
	public void calculateRate_ACUsage() {
		double rate = rateCalculatorService.calculateRate("CAR", "PATROL", true, 1);
		Assert.assertEquals(17, rate, 0);
	}
	
	@Test
	public void calculateRate_passengerExceeds() {
		double rate = rateCalculatorService.calculateRate("CAR", "PATROL", false, 10);
		Assert.assertEquals(20, rate, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calculateRate_undefinedVehicle() {
		rateCalculatorService.calculateRate("HORSECAR", "DIESEL", false, 2);
	}
	
	@Test
	public void calculateDistance_twoCities() {
		List<String> cityList = new ArrayList<>();
		cityList.add("Pune");
		cityList.add("Bangalore");
		double distance = distanceCalculatorService.calculateDistance(cityList);
		Assert.assertEquals(1000, distance, 0);
	}
	
	@Test
	public void calculateDistance_threeCities() {
		List<String> cityList = new ArrayList<>();
		cityList.add("Pune");
		cityList.add("Mumbai");
		cityList.add("Delhi");
		double distance = distanceCalculatorService.calculateDistance(cityList);
		Assert.assertEquals(2250, distance, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calculateDistance_undefinedCity() {
		List<String> cityList = new ArrayList<>();
		cityList.add("Pune");
		cityList.add("Istanbul");
		distanceCalculatorService.calculateDistance(cityList);
	}
	
	
	

}
