package com.cognizant.carrental.services.discount;

import org.springframework.stereotype.Service;


@Service
public class DiscountCalculatorServiceImpl implements DiscountCalculatorService{
	
	private static final double BUS_DISCOUNT = 0.02;
	
	@Override
	public double calculateDiscount(String vehicle) {
		/*this service can easily be expanded in case of extra discount logic is added*/
		if("BUS".equals(vehicle.toUpperCase()))
			return BUS_DISCOUNT;
		return 0;
	}




}
