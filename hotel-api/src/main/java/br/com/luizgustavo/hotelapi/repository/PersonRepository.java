package br.com.luizgustavo.hotelapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.luizgustavo.hotelapi.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query(value = "SELECT p FROM Person p "
			+ "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :param, '%')) "
			+ "OR LOWER(p.document) LIKE LOWER(CONCAT('%', :param, '%')) "
			+ "OR LOWER(p.telephone) LIKE LOWER(CONCAT('%', :param, '%'))")
	List<Person> findByNameOrDocumentOrTelephone(@Param("param") String param);
	
}
