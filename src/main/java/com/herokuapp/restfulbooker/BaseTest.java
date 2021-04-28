package com.herokuapp.restfulbooker;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {

	/**
	 * Creates a booking
	 * 
	 * @return response
	 */
	public static Response createBooking() {
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
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString())
				.post("https://restful-booker.herokuapp.com/booking");
		return response;
	}

	public static Response modifyBooking(int bookingId) {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Tito");
		body.put("lastname", "Trinchera");
		body.put("totalprice", 888);
		body.put("depositpaid", false);

		// Generate a second JSON for inner level parameters
		JSONObject bodyDates = new JSONObject();
		bodyDates.put("checkin", "2021-06-21");
		bodyDates.put("checkout", "2021-06-23");

		// Insert the second JSON in the first one
		body.put("bookingdates", bodyDates);

		// Add last required info
		body.put("additionalneeds", "parking");

		// Get response
		Response response = RestAssured.given().auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).
				body(body.toString()).put("https://restful-booker.herokuapp.com/booking/"+bookingId);
		return response;
	}
}
