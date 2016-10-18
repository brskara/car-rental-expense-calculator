package com.cognizant.carrental.services.vehicle;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class RateCalculatorServiceImpl implements RateCalculatorService{

	private static final Logger logger = LoggerFactory.getLogger(RateCalculatorServiceImpl.class);
	
	/*vehicleMap holds hard coded data for vehicle name and max number of passenger as key value pair*/
	private HashMap<String, Integer> vehicleMap = new HashMap<>(); 
	
	@PostConstruct
	public void initDistanceValues(){
		logger.debug("Vehicle map populating with data...");
		setVehicleMap();
	}


	@Override
	public double calculateRate(String vehicle, String fuelType, boolean hasAC, int numberOfPassengers){
		if(!vehicleMap.containsKey(vehicle)){
			throw new IllegalArgumentException("Vehicle Type is not defined in the system: " + vehicle);
		}
		double rate = 0d;
		if("PATROL".equals(fuelType.toUpperCase())){
			rate = 15;
		}else if("DIESEL".equals(fuelType.toUpperCase())){
			rate = 14;
		}else throw new IllegalArgumentException("Fuel Type is not defined in the system: " + fuelType);
		if(hasAC){
			rate += 2;
		}
		int passengerLimit = vehicleMap.get(vehicle);
		if(numberOfPassengers > passengerLimit){
			rate += numberOfPassengers - passengerLimit ;
		}
		return rate;
	}
	
	
	private void setVehicleMap() {
		vehicleMap.put("CAR", new Integer(5));
		vehicleMap.put("BUS", new Integer(45));
		vehicleMap.put("SUV", new Integer(5));
		vehicleMap.put("VAN", new Integer(15));
		vehicleMap.put("SWIFT", new Integer(4));
		vehicleMap.put("SPORTSCAR", new Integer(2));
		
	}



}
