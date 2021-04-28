package com.herokuapp.resfullbooker;

import java.time.LocalDate;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBookingTests {
	
	@Test
	public void createBookingTest() {
		Response response = createBooking();
		
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

	/**
	 * Creates a booking
	 * @return response
	 */
	public Response createBooking() {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Luis");
		body.put("lastname", "Nunies");
		body.put("totalprice", 777);
		body.put("depositpaid", true);
		
		// Generate a second JSON for inner level parameters
		JSONObject bodyDates = new JSONObject();
		bodyDates.put("checkin", "2020-05-20");
		bodyDates.put("checkout", "2020-05-22");
		
		// Insert the second JSON in the first one
		body.put("bookingdates", bodyDates);
		
		// Add last required info
		body.put("additionalneeds", "minibar");
		
		// Get response
		Response response = RestAssured.given().contentType(ContentType.JSON).
				body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
		return response;
	}

}