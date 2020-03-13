package br.com.luizgustavo.hotelapi.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.luizgustavo.hotelapi.model.Person;

public class PersonForm {

	private Long idPerson;
	@NotNull @NotBlank @Length(max = 50)
	private String nome;
	@NotNull @NotBlank @Length(max = 15)
	private String documento;
	@NotNull @NotBlank @Length(max = 15)
	private String telefone;

	public PersonForm(Long idPerson, String nome, String documento, String telefone) {
		this.idPerson = idPerson;
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
	}
	
	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
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
	
	public Person toEntity() {
		Person person = new Person();
		this.toEntity(person);
		return person;
	}
	
	public void toEntity(Person person) {
		person.setName(this.nome);
		person.setDocument(this.documento);
		person.setTelephone(this.telefone);
	}
}
