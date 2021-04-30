package com.herokuapp.resfullbooker;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.restfulbooker.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBooking extends BaseTest{

	@Test
	public void deleteBooking() {
		// Create a new booking and save response
		Response response = BaseTest.createBooking();
		
		// Save new booking id
		int bookingId = response.jsonPath().getInt("bookingid");
		
		// Delete the specified booking
		Response updatedResponse = RestAssured.given().auth().preemptive().basic("admin", "password123").
				delete("https://restful-booker.herokuapp.com/booking/"+bookingId);
		
		// Verify updated response status
		Assert.assertEquals(updatedResponse.getStatusCode(), 201, "Incorrect response status");
	}
}
