package br.com.luizgustavo.hotelapi.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import br.com.luizgustavo.hotelapi.model.Booking;

public class PriceCalculator {
	
	private static final Double NORMALPRICE = 120.0;
	private static final Double WEEKENDPRICE = 150.0;
	private static final Double NORMALPARKINGPRICE = 15.0;
	private static final Double WEEKENDPARKINGPRICE = 20.0;
	private static final LocalTime LIMITHOURCHECKOUT = LocalTime.of(16, 30);
			
	public static void calculateBookingPrice(Booking booking) {
		LocalDateTime date = booking.getCheckIn();
		
		double price = 0.0;
		while (date.isBefore(booking.getCheckOut())) {
			price += calculatePriceOfDate(date, booking.useParking());			
			date = date.plusDays(1);						
		}

		if (booking.getHourCheckOut().isAfter(LIMITHOURCHECKOUT)) {
			price += calculatePriceOfDate(booking.getCheckOut().plusDays(1), booking.useParking());			
		}

		booking.setPrice(price);
	}
	
	private static double calculatePriceOfDate(LocalDateTime date, boolean useParking) {
		double price = 0.0;
		price += isWeekend(date) ? WEEKENDPRICE : NORMALPRICE;
		if (useParking) {
			price += isWeekend(date) ? WEEKENDPARKINGPRICE : NORMALPARKINGPRICE;
		}
		return price;
	}
	
	private static boolean isWeekend(LocalDateTime date) {
		DayOfWeek day = date.getDayOfWeek();
		return day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY);		
	}

}
