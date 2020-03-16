package br.com.luizgustavo.hotelapi.model.dto;

import java.time.LocalDateTime;

import br.com.luizgustavo.hotelapi.model.Booking;

public class BookingDto {

	private String hospede;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	private Boolean adicionalVeiculo;
	private Double preco;
	
	public BookingDto(Booking booking) {
		this.hospede = booking.getPerson().getName();
		this.dataEntrada = booking.getCheckIn();
		this.dataSaida = booking.getCheckOut();
		this.adicionalVeiculo = booking.useParking();
		this.preco = booking.getPrice();
	}

	public String getHospede() {
		return hospede;
	}

	public void setHospede(String hospede) {
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

}
