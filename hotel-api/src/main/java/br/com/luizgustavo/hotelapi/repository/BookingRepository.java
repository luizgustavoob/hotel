package br.com.luizgustavo.hotelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizgustavo.hotelapi.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
}
