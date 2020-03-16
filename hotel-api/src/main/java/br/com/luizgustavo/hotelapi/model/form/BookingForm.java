package br.com.luizgustavo.hotelapi.model.form;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.luizgustavo.hotelapi.model.Booking;

public class BookingForm {

	@Valid
	private PersonForm hospede;
	@NotNull
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	@NotNull
	private Boolean adicionalVeiculo;
	private Double preco;
	
	public BookingForm() {
	}

	public BookingForm(PersonForm hospede, LocalDateTime dataEntrada, LocalDateTime dataSaida, Boolean adicionalVeiculo,
			Double preco) {
		super();
		this.hospede = hospede;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.adicionalVeiculo = adicionalVeiculo;
		this.preco = preco;
	}

	public PersonForm getHospede() {
		return hospede;
	}

	public void setHospede(PersonForm hospede) {
		this.hospede = hospede;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Boolean getAdicionalVeiculo() {
		return adicionalVeiculo;
	}

	public void setAdicionalVeiculo(Boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Booking toEntity() {
		Booking booking = new Booking();
		this.toEntity(booking);
		return booking;
	}
	
	public void toEntity(Booking booking) {
		booking.setPerson(this.hospede.toEntity());
		booking.setCheckIn(this.dataEntrada);
		booking.setCheckOut(this.dataSaida);
		booking.setUseParking(this.adicionalVeiculo);
		booking.setPrice(this.preco);	
	}
	
}
