package br.com.luizgustavo.hotelapi.model.dto;

import br.com.luizgustavo.hotelapi.model.Person;

public class PersonDto {

	private Long id;
	private String nome;
	private String documento;
	private String telefone;

	public PersonDto(Person person) {
		this.id = person.getIdPerson();
		this.nome = person.getName();
		this.documento = person.getDocument();
		this.telefone = person.getTelephone();
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

}
