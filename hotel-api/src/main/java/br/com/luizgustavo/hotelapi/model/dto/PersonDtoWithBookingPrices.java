package br.com.luizgustavo.hotelapi.model.dto;

public class PersonDtoWithBookingPrices {

	private Long id;
	private String nome;
	private String documento;
	private String telefone;
	private Double ultimaHospedagem;
	private Double totalHospedagens;

	public PersonDtoWithBookingPrices() {
	}

	public PersonDtoWithBookingPrices(Long id, String nome, String documento, String telefone, Double ultimaHospedagem,
			Double totalHospedagens) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
		this.ultimaHospedagem = ultimaHospedagem;
		this.totalHospedagens = totalHospedagens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getUltimaHospedagem() {
		return ultimaHospedagem;
	}

	public void setUltimaHospedagem(Double ultimaHospedagem) {
		this.ultimaHospedagem = ultimaHospedagem;
	}

	public Double getTotalHospedagens() {
		return totalHospedagens;
	}

	public void setTotalHospedagens(Double totalHospedagens) {
		this.totalHospedagens = totalHospedagens;
	}

}
