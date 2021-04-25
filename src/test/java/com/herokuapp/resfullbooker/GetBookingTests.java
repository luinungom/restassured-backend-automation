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
		
		// Check if first name equals Sally
		String firstName = response.jsonPath().getString("firstname");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(firstName, "Sally", "Error, first name does not match the expected one");
		// Check if last name equals Brown
		String lastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(lastName, "Brown", "Error, last name does not match the expected one");
		// Check if total price equals 743
		int totalPrice = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(totalPrice, 743, "Total price does not match the expected one");
		// Check if the deposit has been paid
		boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertEquals(true, depositPaid, "Deposit should be paid but it is not");
		// Check if check in date is 2015-07-15
		LocalDate checkingDate = LocalDate.parse(response.jsonPath().getString("bookingdates.checkin"));
		boolean checkingMatch = (checkingDate.isEqual(LocalDate.parse("2015-05-03")));

		softAssert.assertAll();
	}
	
	

}
