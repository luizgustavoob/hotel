package br.com.luizgustavo.hotelapi.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.luizgustavo.hotelapi.model.Person;

public class PersonForm {

	private Long id;
	@NotNull @NotBlank @Length(max = 50)
	private String nome;
	@NotNull @NotBlank @Length(max = 15)
	private String documento;
	@NotNull @NotBlank @Length(max = 15)
	private String telefone;
	
	public PersonForm() {
	}

	public PersonForm(Long id, String nome, String documento, String telefone) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
	}
	
	public PersonForm(String nome, String documento, String telefone) {
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
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
	
	public Person toEntity() {
		Person person = new Person();
		this.toEntity(person);
		return person;
	}
	
	public void toEntity(Person person) {
		person.setIdPerson(this.id);
		person.setName(this.nome);
		person.setDocument(this.documento);
		person.setTelephone(this.telefone);
	}
	
}
