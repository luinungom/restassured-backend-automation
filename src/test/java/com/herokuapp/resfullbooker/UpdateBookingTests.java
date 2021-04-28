package com.herokuapp.resfullbooker;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.restfulbooker.BaseTest;

import io.restassured.response.Response;

public class UpdateBookingTests extends BaseTest{

	@Test
	public void updateBookingTest() {
		// Create a new booking
		Response response = BaseTest.createBooking();
		
		// Get new created booking id
		int bookingId = response.jsonPath().getInt("bookingid");
		
		// Update the booking and save new response
		Response updatedResponse = BaseTest.modifyBooking(bookingId);
		Assert.assertEquals(updatedResponse.statusCode(), 200, "Incorrect status code");
	
		// Test response body
		SoftAssert softAssert = new SoftAssert();
		String firstName = updatedResponse.jsonPath().getString("firstname");
		softAssert.assertEquals(firstName, "Tito", "First name is incorrect");
		String lastName = updatedResponse.jsonPath().getString("lastname");
		softAssert.assertEquals(lastName, "Trinchera", "Last name is incorrect");
		int totalprice = updatedResponse.jsonPath().getInt("totalprice");
		softAssert.assertEquals(totalprice, 888, "Total price is incorrect");
		boolean depositpaid = updatedResponse.jsonPath().getBoolean("depositpaid");
		softAssert.assertFalse(depositpaid);
		LocalDate checkingDate = LocalDate.parse(updatedResponse.jsonPath().getString("bookingdates.checkin"));
		boolean checkingMatch = (checkingDate.isEqual(LocalDate.parse("2021-06-21")));
		softAssert.assertTrue(checkingMatch, "Checking date is incorrect");
		LocalDate checkoutDate = LocalDate.parse(updatedResponse.jsonPath().getString("bookingdates.checkout"));
		boolean checkoutMatch = (checkoutDate.isEqual(LocalDate.parse("2021-06-23")));
		softAssert.assertTrue(checkoutMatch, "Checkout date is incorrect");
		softAssert.assertAll();
	}
}
