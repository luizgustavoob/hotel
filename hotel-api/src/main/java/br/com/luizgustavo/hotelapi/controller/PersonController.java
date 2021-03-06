package br.com.luizgustavo.hotelapi.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luizgustavo.hotelapi.model.dto.PersonDto;
import br.com.luizgustavo.hotelapi.model.form.PersonForm;
import br.com.luizgustavo.hotelapi.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@PostMapping
	public ResponseEntity<PersonDto> insert(@Valid @RequestBody PersonForm form, HttpServletResponse response) {
		PersonDto person = this.service.insert(form);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(person.getId())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<PersonDto>> findAll(Pageable pageable) {
		Page<PersonDto> people = this.service.findAll(pageable);
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
	
	@GetMapping("/filter")
	public ResponseEntity<List<PersonDto>> findByNameOrDocumentOrTelephone(@RequestParam("param") String param) {
		List<PersonDto> people = this.service.findByNameOrDocumentOrTelephone(param);
		return ResponseEntity.ok(people);
	}
	
	@GetMapping("/filterByDocument")
	@ResponseStatus(code = HttpStatus.OK)
	public boolean findByDocument(@RequestParam("param") String param) {
		return this.service.findByDocument(param);
	}
	
}
