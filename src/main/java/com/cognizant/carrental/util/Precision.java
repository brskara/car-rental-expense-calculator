package com.cognizant.carrental.util;

public class Precision {
	
	public static double round(double value, int precision){
		value = value * Math.pow(10, precision);
		value = (double)((int) value);
		value = value /Math.pow(10, precision);
		return value;
	}

}
