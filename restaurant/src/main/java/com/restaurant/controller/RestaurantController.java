package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.serviceImpl.RestaurantServiceImpl;

@RestController
public class RestaurantController {

	@Autowired
	RestaurantServiceImpl restaurantServiceImpl;

	@RequestMapping(value = "food/satisfaction", method = RequestMethod.GET)
	public String getMaxStatisfaction(@RequestParam String customerName, @RequestParam int maxTime) {

	  try{	
		return restaurantServiceImpl.calculateMaxSatisfaction(customerName, maxTime);
	  } catch(Exception e){
		  return "Unable to process your request. Please try again";
	  }
	}

}
