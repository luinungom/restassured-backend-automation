package com.herokuapp.resfullbooker;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.restfulbooker.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;


public class GetBookingTests extends BaseTest{
	
	@Test
	public void getBookingTest() {
		// Create a new booking
		Response firstResponse = BaseTest.createBooking();		
		
		// Set path parameter
		spec.pathParam("bookingId", firstResponse.jsonPath().getInt("bookingid"));
		
		// Get response with booking ids
		Response response = RestAssured.given(spec).get("/booking/{bookingId}");
		// Check if response code equals 200
		Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
		
		// Check if first name equals Luis
		String firstName = response.jsonPath().getString("firstname");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(firstName, "Luis", "Error, first name does not match the expected one");
		// Check if last name equals Nunies
		String lastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(lastName, "Nunies", "Error, last name does not match the expected one");
		// Check if total price equals 403
		int totalPrice = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(totalPrice, 777, "Total price does not match the expected one");
		// Check if the deposit has been paid
		boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertEquals(true, depositPaid, "Deposit should be paid but it is not");
		// Check if checking date is 2020-05-20
		LocalDate checkingDate = LocalDate.parse(response.jsonPath().getString("bookingdates.checkin"));
		boolean checkingMatch = (checkingDate.isEqual(LocalDate.parse("2020-05-20")));
		softAssert.assertTrue(checkingMatch, "Checking date is incorrect");
		// Check if checkout date is 2020-05-22
		LocalDate checkoutDate = LocalDate.parse(response.jsonPath().getString("bookingdates.checkout"));
		boolean checkoutMatch = (checkoutDate.isEqual(LocalDate.parse("2020-05-22")));
		softAssert.assertTrue(checkoutMatch, "Checkout date is incorrect");
		softAssert.assertAll();
	}
	
	@Test
	public void getBookingXMLTest() {
		// Create a new booking
		Response firstResponse = BaseTest.createBooking();		
		
		// Set path parameter
		spec.pathParam("bookingId", firstResponse.jsonPath().getInt("bookingid"));
		
		// Get response with booking ids
		Header xml = new Header("Accept", "application/xml");
		spec.header(xml);
		Response response = RestAssured.given(spec).get("/booking/{bookingId}");
		// Check if response code equals 200
		Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
		
		// Check if first name equals Luis
		String firstName = response.xmlPath().getString("booking.firstname");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(firstName, "Luis", "Error, first name does not match the expected one");
		// Check if last name equals Nunies
		String lastName = response.xmlPath().getString("booking.lastname");
		softAssert.assertEquals(lastName, "Nunies", "Error, last name does not match the expected one");
		// Check if total price equals 403
		int totalPrice = response.xmlPath().getInt("booking.totalprice");
		softAssert.assertEquals(totalPrice, 777, "Total price does not match the expected one");
		// Check if the deposit has been paid
		boolean depositPaid = response.xmlPath().getBoolean("booking.depositpaid");
		softAssert.assertEquals(true, depositPaid, "Deposit should be paid but it is not");
		// Check if checking date is 2020-05-20
		LocalDate checkingDate = LocalDate.parse(response.xmlPath().getString("booking.bookingdates.checkin"));
		boolean checkingMatch = (checkingDate.isEqual(LocalDate.parse("2020-05-20")));
		softAssert.assertTrue(checkingMatch, "Checking date is incorrect");
		// Check if checkout date is 2020-05-22
		LocalDate checkoutDate = LocalDate.parse(response.xmlPath().getString("booking.bookingdates.checkout"));
		boolean checkoutMatch = (checkoutDate.isEqual(LocalDate.parse("2020-05-22")));
		softAssert.assertTrue(checkoutMatch, "Checkout date is incorrect");
		softAssert.assertAll();
	}
	
	

}
