package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	
	protected static RequestSpecification spec;
	
	@BeforeMethod
	protected void setUp() {
		spec = new RequestSpecBuilder().
				setBaseUri("https://restful-booker.herokuapp.com").build();
	}

	/**
	 * Creates a booking
	 * 
	 * @return response
	 */
	protected static Response createBooking() {
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
		Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/booking");
		return response;
	}
	
	/**
	 * Creates a booking using POJO
	 * 
	 * @return response
	 */
	protected static Response createBookingWithPOJO() {
		// Create dates
		BookingDates pojoDates = new BookingDates("2020-05-20", "2020-05-22");
		// Create a Booking object
		Booking pojoBooking = new Booking("Luis", "Nunies", 777, true, pojoDates, "minibar");

		// Get response
		Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(pojoBooking).post("/booking");
		response.print();
		return response;
	}

	protected static Response modifyBooking(int bookingId) {
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
		Response response = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).
				body(body.toString()).put("/booking/"+bookingId);
		return response;
	}
	
	protected static Response partialUpdateBooking(int bookingId) {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Jeremias");
		
		// Generate a second JSON for inner level parameters
		JSONObject bodyDates = new JSONObject();
		bodyDates.put("checkin", "2025-12-31");
		
		// Insert the second JSON in the first one
		body.put("bookingdates", bodyDates);
		
		// Get response
		Response response = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).
				body(body.toString()).patch("/booking/"+bookingId);
		return response;
	}
}
