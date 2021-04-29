package com.herokuapp.resfullbooker;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.restfulbooker.BaseTest;

import io.restassured.response.Response;

public class PartialUpdateBookingTests extends BaseTest {

	@Test
	public void partialUpdateBookingTest() {
		// Create a new booking
		Response response = BaseTest.createBooking();

		// Get new created booking id
		int bookingId = response.jsonPath().getInt("bookingid");

		// Update the booking and save new response
		Response updatedResponse = BaseTest.partialUpdateBooking(bookingId);
		Assert.assertEquals(updatedResponse.statusCode(), 200, "Incorrect status code");

		// Test response body
		SoftAssert softAssert = new SoftAssert();
		String firstName = updatedResponse.jsonPath().getString("firstname");
		softAssert.assertEquals(firstName, "Jeremias", "First name is incorrect");
		LocalDate checkingDate = LocalDate.parse(updatedResponse.jsonPath().getString("bookingdates.checkin"));
		boolean checkingMatch = (checkingDate.isEqual(LocalDate.parse("2025-12-31")));
		softAssert.assertTrue(checkingMatch, "Checking date is incorrect");
		softAssert.assertAll();
	}

}
