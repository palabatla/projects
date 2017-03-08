package com.restaurant.serviceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceImplTest {

	@InjectMocks
	RestaurantServiceImpl resturantServiceImpl;

	@Test
	public void calculateMaxSatisfactionTest1() {

		String expected = "test1 has maximum satisfaction amount of 84493 for eating in 43secs with time limit of 6000secs";

		String actual = resturantServiceImpl.calculateMaxSatisfaction("test1", 100);

		assertEquals(expected, actual);

	}

	@Test
	public void calculateMaxSatisfactionTest2() {

		String expected = "No maximum satisfaction amount available for test2 in the given time limit 60secs";

		String actual = resturantServiceImpl.calculateMaxSatisfaction("test2", 1);

		assertEquals(expected, actual);

	}

}
