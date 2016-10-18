package com.cognizant.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.cognizant.carrental")
public class CarRentalCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalCalculatorApplication.class, args);
	}
}
