package br.com.luizgustavo.hotelapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luizgustavo.hotelapi.model.Person;
import br.com.luizgustavo.hotelapi.model.dto.PersonDto;
import br.com.luizgustavo.hotelapi.model.form.PersonForm;
import br.com.luizgustavo.hotelapi.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
	public PersonDto insert(PersonForm form) {
		Person person = form.toEntity();
		person = this.repository.save(person);
		return new PersonDto(person);
	}
	
	public PersonDto update(Long idPerson, PersonForm form) {
		Person person = this.repository.findById(idPerson).orElseThrow(() -> new EmptyResultDataAccessException(1));
		person.setName(form.getNome());
		person.setDocument(form.getDocumento());
		person.setTelephone(form.getTelefone());
		person = this.repository.save(person);
		return new PersonDto(person);
	}
	
	public void delete(Long idPerson) {
		this.repository.deleteById(idPerson);
	}
	
	public PersonDto findById(Long idPerson) {
		Person person = this.repository.findById(idPerson).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return new PersonDto(person);
	}
	
	public List<PersonDto> findAll() {
		return this.repository.findAll().stream().map(person -> new PersonDto(person)).collect(Collectors.toList());
	}
	
	public Page<PersonDto> findAll(Pageable pageable) {
		return this.repository.findAll(pageable).map(person -> new PersonDto(person));		
	}
	
	public List<PersonDto> findByNameOrDocumentOrTelephone(String param) {
		return this.repository.findByNameOrDocumentOrTelephone(param).stream().map(p -> new PersonDto(p)).collect(Collectors.toList());
	}
	
	public boolean findByDocument(String param) {
		Person person = this.repository.findByDocument(param).orElse(null);
		return person != null;
	}
	
}
