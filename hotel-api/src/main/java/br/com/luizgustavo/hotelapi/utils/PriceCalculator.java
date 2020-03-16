package br.com.luizgustavo.hotelapi.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.luizgustavo.hotelapi.model.Booking;

public class PriceCalculator {
	
	private static final Double NORMALPRICE = 120.0;
	private static final Double WEEKENDPRICE = 150.0;
	private static final Double NORMALPARKINGPRICE = 15.0;
	private static final Double WEEKENDPARKINGPRICE = 20.0;
	private static final LocalTime LIMITHOURCHECKOUT = LocalTime.of(16, 30);
			
	public static void calculateBookingPrice(Booking booking) {
		LocalDate date = booking.getCheckIn().toLocalDate();
		
		double price = 0.0;
		while (date.isBefore(booking.getCheckOut().toLocalDate())) {
			price += calculatePriceOfDate(date, booking.useParking());			
			date = date.plusDays(1);						
		}

		if (booking.getHourCheckOut().isAfter(LIMITHOURCHECKOUT)) {
			price += calculatePriceOfDate(booking.getCheckOut().plusDays(1).toLocalDate(), booking.useParking());			
		}

		booking.setPrice(price);
	}
	
	private static double calculatePriceOfDate(LocalDate date, boolean useParking) {
		double price = 0.0;
		price += isWeekend(date) ? WEEKENDPRICE : NORMALPRICE;
		if (useParking) {
			price += isWeekend(date) ? WEEKENDPARKINGPRICE : NORMALPARKINGPRICE;
		}
		return price;
	}
	
	private static boolean isWeekend(LocalDate date) {
		DayOfWeek day = date.getDayOfWeek();
		return day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY);		
	}

}
