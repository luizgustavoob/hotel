package br.com.luizgustavo.hotelapi.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import br.com.luizgustavo.hotelapi.model.dto.PeopleAndPrices;

@Entity
@Table(name = "person")
@NamedNativeQuery(name = "Person.findByBookingStatus", 
	resultSetMapping = "peopleAndBookingPrices", 
	query = "SELECT DISTINCT p.idPerson as id, p.name as nome, p.document as documento, p.telephone as telefone,"
			+ "			 (SELECT b2.price "
			+ "			    FROM booking b2"
			+ "   		   WHERE b2.idperson = p.idperson "
			+ "			     AND b2.checkin = (SELECT max(b3.checkin) "
			+ "				                     FROM booking b3 "
			+ "			                        WHERE b3.idperson = b2.idperson)) AS ultimaHospedagem,"
			+ "			 (SELECT SUM(b2.price)"
			+ "				FROM booking b2"
			+ " 		   WHERE b2.idperson = p.idperson) AS totalHospedagens "
			+ "     FROM person p "
			+ "     LEFT JOIN booking b ON p.idperson = b.idperson "
			+ "    WHERE (b.checkout IS NULL and :checkOutNull = 'S') OR (b.checkout IS NOT NULL and :checkOutNull = 'N') ")
@SqlResultSetMapping(name = "peopleAndBookingPrices", classes = {
	@ConstructorResult(targetClass = PeopleAndPrices.class, columns = {
			@ColumnResult(name = "id", type = Long.class),
			@ColumnResult(name = "nome", type = String.class),
			@ColumnResult(name = "documento", type = String.class),
			@ColumnResult(name = "telefone", type = String.class),
			@ColumnResult(name = "ultimaHospedagem", type = Double.class),
			@ColumnResult(name = "totalHospedagens", type = Double.class) }) })
public class Person {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idperson")
	private Long idPerson;
	@Column(length = 50)
	private String name;
	@Column(unique = true, length = 15) 
	private String document;
	@Column(length = 15)
	private String telephone;

	public Person() {
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPerson == null) ? 0 : idPerson.hashCode());
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
		Person other = (Person) obj;
		if (idPerson == null) {
			if (other.idPerson != null)
				return false;
		} else if (!idPerson.equals(other.idPerson))
			return false;
		return true;
	}

}
