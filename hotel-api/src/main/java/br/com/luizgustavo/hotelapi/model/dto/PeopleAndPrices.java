package br.com.luizgustavo.hotelapi.model.dto;

import java.time.LocalDateTime;

public class PeopleAndPrices {

	private Long idbooking;
	private Long idperson;
	private String nome;
	private String documento;
	private String telefone;
	private LocalDateTime dataentrada;
	private LocalDateTime datasaida;
	private Boolean adicionalveiculo;
	private Double preco;
	private Double precototal;

	public PeopleAndPrices() {
	}

	public PeopleAndPrices(Long idbooking, LocalDateTime dataentrada, LocalDateTime datasaida, Boolean adicionalveiculo, Double preco, Double precoTotal, 
			Long idperson, String nome, String documento, String telefone) {
		this.idbooking = idbooking;
		this.dataentrada = dataentrada;
		this.datasaida = datasaida;
		this.adicionalveiculo = adicionalveiculo;
		this.preco = preco;
		this.precototal = precoTotal;
		this.idperson = idperson;
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
	}

	public Long getIdbooking() {
		return idbooking;
	}

	public void setIdbooking(Long idbooking) {
		this.idbooking = idbooking;
	}

	public Long getIdperson() {
		return idperson;
	}

	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDateTime getDataentrada() {
		return dataentrada;
	}

	public void setDataentrada(LocalDateTime dataentrada) {
		this.dataentrada = dataentrada;
	}

	public LocalDateTime getDatasaida() {
		return datasaida;
	}

	public void setDatasaida(LocalDateTime datasaida) {
		this.datasaida = datasaida;
	}

	public Boolean getAdicionalveiculo() {
		return adicionalveiculo;
	}

	public void setAdicionalveiculo(Boolean adicionalveiculo) {
		this.adicionalveiculo = adicionalveiculo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getPrecototal() {
		return precototal;
	}

	public void setPrecototal(Double precototal) {
		this.precototal = precototal;
	}

}
