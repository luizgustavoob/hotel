package br.com.luizgustavo.hotelapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luizgustavo.hotelapi.model.Booking;
import br.com.luizgustavo.hotelapi.model.dto.BookingDto;
import br.com.luizgustavo.hotelapi.model.form.BookingForm;
import br.com.luizgustavo.hotelapi.repository.BookingRepository;
import br.com.luizgustavo.hotelapi.utils.PriceCalculator;

@Service
public class BookingService {

	@Autowired
	private BookingRepository repository;
	
	public BookingDto insert(BookingForm form) {
		Booking booking = form.toEntity();
		if (booking.getCheckOut() != null) {
			PriceCalculator.calculateBookingPrice(booking);
		}
		booking = this.repository.save(booking);
		return new BookingDto(booking);
	}
	
	public BookingDto update(Long idBooking, BookingForm form) {
		Booking booking = this.repository.findById(idBooking).orElseThrow(() -> new EmptyResultDataAccessException(1));
		form.toEntity(booking);
		if (booking.getCheckOut() != null) {
			PriceCalculator.calculateBookingPrice(booking);
		}
		booking = this.repository.save(booking);
		return new BookingDto(booking);
	}
	
	public void delete(Long idBooking) {
		this.repository.deleteById(idBooking);
	}
	
	public BookingDto findById(Long idBooking) {
		Booking booking = this.repository.findById(idBooking).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return new BookingDto(booking);
	}
	
	public List<BookingDto> findAll() {
		return this.repository.findAll().stream().map(booking -> new BookingDto(booking)).collect(Collectors.toList());
	}
	
	public List<BookingDto> findAll(Pageable pageable) {
		return this.repository.findAll(pageable).stream().map(booking -> new BookingDto(booking)).collect(Collectors.toList());		
	}
}
