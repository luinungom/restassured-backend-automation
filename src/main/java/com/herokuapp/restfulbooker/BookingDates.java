package com.herokuapp.restfulbooker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * For POJO practice only
 * 
 * @author Luis Núñez
 *
 */
public class BookingDates {

	private LocalDate checkin;
	private LocalDate checkout;

	public BookingDates(String checkin, String checkout) {
		this.checkin = setCheckin(checkin);
		this.checkout = setCheckout(checkout);

	}

	public String getCheckin() {
		return checkin.toString();
	}

	/**
	 * Validates the correct checking date creation
	 * 
	 * @param checkin String
	 * @return localChecking LocalDate
	 */
	public LocalDate setCheckin(String checkin) {
		LocalDate localCheckin = null;
		try {
			localCheckin = LocalDate.parse(checkin);
		} catch (DateTimeParseException dateException) {
			System.err.println("Unable to parse the provided checking date");
		}
		return localCheckin;
	}

	public String getCheckout() {
		return checkout.toString();
	}

	/**
	 * Validates the correct checkout date creation
	 * 
	 * @param checkout String
	 * @return localCheckout LocalDate
	 */
	public LocalDate setCheckout(String checkout) {
		LocalDate localCheckout = null;
		try {
			localCheckout = LocalDate.parse(checkout);
		} catch (DateTimeParseException dateException) {
			System.err.println("Unable to parse the provided checkout date");
		}
		return localCheckout;
	}

	@Override
	public String toString() {
		return "BookingDates [checkin=" + checkin + ", checkout=" + checkout + "]";
	}
	
	

}
