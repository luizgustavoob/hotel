package br.com.luizgustavo.hotelapi.model.dto;

import java.time.LocalDateTime;

import br.com.luizgustavo.hotelapi.model.Booking;

public class BookingDto {

	private Long id;
	private PersonDto hospede;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	private Boolean adicionalVeiculo;
	private Double preco;
	private Double precoTotal;
	
	public BookingDto(PeopleAndPrices peopleAndPrices) {
		this.id = peopleAndPrices.getIdbooking();
		this.hospede = new PersonDto(peopleAndPrices.getIdperson(), peopleAndPrices.getNome(), peopleAndPrices.getDocumento(), peopleAndPrices.getTelefone());
		this.dataEntrada = peopleAndPrices.getDataentrada();
		this.dataSaida = peopleAndPrices.getDatasaida();
		this.adicionalVeiculo = peopleAndPrices.getAdicionalveiculo();
		this.preco = peopleAndPrices.getPreco();
		this.precoTotal = peopleAndPrices.getPrecototal();
	}
	
	public BookingDto(Booking booking) {
		this.id = booking.getIdBooking();
		this.hospede = new PersonDto(booking.getPerson());
		this.dataEntrada = booking.getCheckIn();
		this.dataSaida = booking.getCheckOut();
		this.adicionalVeiculo = booking.useParking();
		this.preco = booking.getPrice();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonDto getHospede() {
		return hospede;
	}

	public void setHospede(PersonDto hospede) {
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

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}
	
}
