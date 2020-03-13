package br.com.luizgustavo.hotelapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	public PersonDto insert(PersonForm form) {
		Person person = form.toEntity();
		person = this.repository.save(person);
		return new PersonDto(person);
	}
	
	public PersonDto update(Long idPerson, PersonForm form) {
		Person person = this.repository.findById(idPerson).orElseThrow(() -> new EmptyResultDataAccessException(1));
		form.toEntity(person);
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
	
	public List<PersonDto> findAll(Pageable pageable) {
		return this.repository.findAll(pageable).stream().map(person -> new PersonDto(person)).collect(Collectors.toList());		
	}
}
