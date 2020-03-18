package br.com.luizgustavo.hotelapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luizgustavo.hotelapi.model.Booking;
import br.com.luizgustavo.hotelapi.model.dto.BookingDto;
import br.com.luizgustavo.hotelapi.model.dto.PeopleAndPrices;
import br.com.luizgustavo.hotelapi.model.form.BookingForm;
import br.com.luizgustavo.hotelapi.repository.BookingRepository;
import br.com.luizgustavo.hotelapi.utils.PriceCalculator;

@Service
public class BookingService {

	@Autowired
	private BookingRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
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
	
	public Page<BookingDto> findAll(Pageable pageable, char checkOutNull) {
		TypedQuery<PeopleAndPrices> query = manager.createNamedQuery("Booking.peopleAndPrices", PeopleAndPrices.class);
		query.setParameter("checkOutNull", checkOutNull);
		List<PeopleAndPrices> people = query.getResultList();
		
		int page = pageable.getPageNumber();
		int size = pageable.getPageSize();
		int first = page * size;
		
		List<PeopleAndPrices> peoplePaginated = query.setFirstResult(first).setMaxResults(size).getResultList();
		
		List<BookingDto> returnList = peoplePaginated.stream().map(p -> new BookingDto(p)).collect(Collectors.toList());
		
		return new PageImpl<BookingDto>(returnList, pageable, people.size());
	}
}
