package com.herokuapp.resfullbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class GetBookingIdsTests {

	@Test
	public void getBookingIdsWithoutFilterTest() {
		// Get response with booking ids
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
		// Check if response code equals 200
		Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
		// Verify at least 1 booking in response
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertTrue(bookingIds.size() > 0, "Error, booking list is empty");
	}
	
	@Test
	public void getBookingWithIdsTest() {
		// Get response with booking ids
		CreateBookingTests newBooking = new CreateBookingTests();
		Response response = newBooking.createBooking();
		// Check if response code equals 200
		Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
		// Check if first name equals Sally
		String firstName = response.jsonPath().getString("firstname");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(firstName, "Luis", "Error, first name does not match the expected one");
		// Check if last name equals Brown
		String lastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(lastName, "Nunies", "Error, last name does not match the expected one");
		softAssert.assertAll();
	}

}
