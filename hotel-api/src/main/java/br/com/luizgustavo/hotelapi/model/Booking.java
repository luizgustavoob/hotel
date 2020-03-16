package br.com.luizgustavo.hotelapi.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBooking;
	@ManyToOne @JoinColumn(name = "idperson", referencedColumnName = "idperson")
	private Person person;
	@Column(name = "checkin")
	private LocalDateTime checkIn;
	@Column(name = "checkout")
	private LocalDateTime checkOut;
	private Boolean parking;
	private Double price;

	public Booking() {
	}

	public Long getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(Long idBooking) {
		this.idBooking = idBooking;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public LocalDateTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDateTime checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDateTime getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDateTime checkOut) {
		this.checkOut = checkOut;
	}

	public Boolean useParking() {
		return this.parking;
	}

	public void setUseParking(Boolean parking) {
		this.parking = parking;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public LocalTime getHourCheckOut() {
		return LocalTime.of(this.checkOut.getHour(), this.checkOut.getMinute());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBooking == null) ? 0 : idBooking.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (idBooking == null) {
			if (other.idBooking != null)
				return false;
		} else if (!idBooking.equals(other.idBooking))
			return false;
		return true;
	}

}
