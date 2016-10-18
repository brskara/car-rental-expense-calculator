package com.cognizant.carrental.services.distance;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class DistanceCalculatorServiceImpl implements DistanceCalculatorService{

	private static final Logger logger = LoggerFactory.getLogger(DistanceCalculatorServiceImpl.class);
	
	/*cityMap holds hard coded data for city name and its corresponding index value as key value pair*/
	private HashMap<String, Integer> cityMap = new HashMap<>();
	
	/*distanceMap holds distance values between cities*/
	private double[][] distanceMap = null;

	@PostConstruct
	public void initDistanceValues(){
		logger.debug("City map and distance array populating with data...");
		setCityMap();
		setDistances();
	}
	
	@Override
	public double calculateDistance(List<String> cities) {
		double distance = 0d;
		String prevCity = null;
		for(String city: cities){
			if(!cityMap.containsKey(city.toUpperCase())){
				throw new IllegalArgumentException("City is not defined in the system: " + city);
			}
			if(prevCity == null){
				prevCity = city;
				continue;
			}
			distance += distanceMap[cityMap.get(prevCity.toUpperCase())][cityMap.get(city.toUpperCase())];
			prevCity = city;
		}
		return distance;
	}
	
	
	private void setCityMap() {
		cityMap.put("PUNE", new Integer(0));
		cityMap.put("MUMBAI", new Integer(1));
		cityMap.put("BANGALORE", new Integer(2));
		cityMap.put("DELHI", new Integer(3));
		cityMap.put("CHENNAI", new Integer(4));
	}
	
	
	private void setDistances() {
		//numbers are arbitrarily selected
		distanceMap = new double[][]{
				  { 0, 200, 1000, 345, 1188.5},
				  { 200, 0, 873, 2050, 1234.5},
				  { 1000, 873, 0, 657, 1256},
				  { 345, 2050, 657, 0, 543.3},
				  { 1188.5, 1234.5, 1256, 543.3, 0}
				};
	}



}
