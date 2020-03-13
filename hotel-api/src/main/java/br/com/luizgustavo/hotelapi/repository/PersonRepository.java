package br.com.luizgustavo.hotelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizgustavo.hotelapi.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
