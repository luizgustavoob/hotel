package br.com.luizgustavo.hotelapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizgustavo.hotelapi.model.dto.PersonDto;
import br.com.luizgustavo.hotelapi.model.form.PersonForm;
import br.com.luizgustavo.hotelapi.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@PostMapping
	public ResponseEntity<PersonDto> insert(@Valid @RequestBody PersonForm form) {
		PersonDto person = this.service.insert(form);
		return ResponseEntity.ok(person);
	}
	
	@GetMapping("/page")
	public ResponseEntity<List<PersonDto>> findAll(Pageable pageable) {
		List<PersonDto> people = this.service.findAll(pageable);
		return ResponseEntity.ok(people);
	}
	
	@GetMapping
	public ResponseEntity<List<PersonDto>> findAll() {
		List<PersonDto> people = this.service.findAll();
		return ResponseEntity.ok(people);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonDto> findById(@PathVariable("id") Long idPerson) {
		PersonDto person = this.service.findById(idPerson);
		return ResponseEntity.ok(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long idPerson) {
		this.service.delete(idPerson);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonDto> update(@PathVariable("id") Long idPerson, @Valid @RequestBody PersonForm form) {
		PersonDto person = this.service.update(idPerson, form);
		return ResponseEntity.ok(person);
	}
	
}
