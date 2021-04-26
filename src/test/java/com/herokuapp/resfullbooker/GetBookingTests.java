package com.herokuapp.resfullbooker;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetBookingTests {
	
	@Test
	public void getBookingTest() {
		// Get response with booking ids
		Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/5");
		// Check if response code equals 200
		Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
		
		// Check if first name equals Jim
		String firstName = response.jsonPath().getString("firstname");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(firstName, "Jim", "Error, first name does not match the expected one");
		// Check if last name equals Ericsson
		String lastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(lastName, "Ericsson", "Error, last name does not match the expected one");
		// Check if total price equals 403
		int totalPrice = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(totalPrice, 403, "Total price does not match the expected one");
		// Check if the deposit has been paid
		boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertEquals(true, depositPaid, "Deposit should be paid but it is not");
		// Check if checking date is 2016-03-10
		LocalDate checkingDate = LocalDate.parse(response.jsonPath().getString("bookingdates.checkin"));
		boolean checkingMatch = (checkingDate.isEqual(LocalDate.parse("2016-03-10")));
		softAssert.assertTrue(checkingMatch, "Checking date is incorrect");
		// Check if checkout date is 2020-03-31
		LocalDate checkoutDate = LocalDate.parse(response.jsonPath().getString("bookingdates.checkout"));
		boolean checkoutMatch = (checkoutDate.isEqual(LocalDate.parse("2020-03-31")));
		softAssert.assertTrue(checkoutMatch, "Checkout date is incorrect");
		softAssert.assertAll();
	}
	
	

}
