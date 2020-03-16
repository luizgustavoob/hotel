package br.com.luizgustavo.hotelapi.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luizgustavo.hotelapi.model.dto.BookingDto;
import br.com.luizgustavo.hotelapi.model.form.BookingForm;
import br.com.luizgustavo.hotelapi.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService service;
	
	@PostMapping
	public ResponseEntity<BookingDto> insert(@Valid @RequestBody BookingForm form, HttpServletResponse response) {
		BookingDto booking = this.service.insert(form);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(booking.getId())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.status(HttpStatus.CREATED).body(booking);
	}
	
	@GetMapping("/page")
	public ResponseEntity<List<BookingDto>> findAll(Pageable pageable) {
		List<BookingDto> bookings = this.service.findAll(pageable);
		return ResponseEntity.ok(bookings);
	}
	
	@GetMapping
	public ResponseEntity<List<BookingDto>> findAll() {
		List<BookingDto> bookings = this.service.findAll();
		return ResponseEntity.ok(bookings);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookingDto> findById(@PathVariable("id") Long idBooking) {
		BookingDto booking = this.service.findById(idBooking);
		return ResponseEntity.ok(booking);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long idBooking) {
		this.service.delete(idBooking);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BookingDto> update(@PathVariable("id") Long idBooking, @Valid @RequestBody BookingForm form) {
		BookingDto booking = this.service.update(idBooking, form);
		return ResponseEntity.ok(booking);
	}
}
