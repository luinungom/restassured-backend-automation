package com.herokuapp.resfullbooker;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.restfulbooker.BaseTest;

import io.restassured.response.Response;

public class CreateBookingTests extends BaseTest {
	
	@Test
	public void createBookingTest() {
		Response response = BaseTest.createBooking();
		
		// Test response status code
		Assert.assertEquals(response.statusCode(), 200, "Incorrect status code");
		
		// Test response body
		SoftAssert softAssert = new SoftAssert();
		String firstName = response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(firstName, "Luis", "First name is incorrect");
		String lastName = response.jsonPath().getString("booking.lastname");
		softAssert.assertEquals(lastName, "Nunies", "Last name is incorrect");
		int totalprice = response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(totalprice, 777, "Total price is incorrect");
		boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertTrue(depositpaid);
		LocalDate checkingDate = LocalDate.parse(response.jsonPath().getString("booking.bookingdates.checkin"));
		boolean checkingMatch = (checkingDate.isEqual(LocalDate.parse("2020-05-20")));
		softAssert.assertTrue(checkingMatch, "Checking date is incorrect");
		LocalDate checkoutDate = LocalDate.parse(response.jsonPath().getString("booking.bookingdates.checkout"));
		boolean checkoutMatch = (checkoutDate.isEqual(LocalDate.parse("2020-05-22")));
		softAssert.assertTrue(checkoutMatch, "Checkout date is incorrect");
		softAssert.assertAll();
	}
	
	@Test
	public void createBookingTestWithPojo() {
		Response response = BaseTest.createBookingWithPOJO();
		
		// Test response status code
		Assert.assertEquals(response.statusCode(), 200, "Incorrect status code");
		
		// Test response body
		SoftAssert softAssert = new SoftAssert();
		String firstName = response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(firstName, "Luis", "First name is incorrect");
		String lastName = response.jsonPath().getString("booking.lastname");
		softAssert.assertEquals(lastName, "Nunies", "Last name is incorrect");
		int totalprice = response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(totalprice, 777, "Total price is incorrect");
		boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertTrue(depositpaid);
		LocalDate checkingDate = LocalDate.parse(response.jsonPath().getString("booking.bookingdates.checkin"));
		boolean checkingMatch = (checkingDate.isEqual(LocalDate.parse("2020-05-20")));
		softAssert.assertTrue(checkingMatch, "Checking date is incorrect");
		LocalDate checkoutDate = LocalDate.parse(response.jsonPath().getString("booking.bookingdates.checkout"));
		boolean checkoutMatch = (checkoutDate.isEqual(LocalDate.parse("2020-05-22")));
		softAssert.assertTrue(checkoutMatch, "Checkout date is incorrect");
		softAssert.assertAll();
	}

}
