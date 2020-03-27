package de.schuetze.demo.model.data;

import org.springframework.data.jpa.repository.JpaRepository;

import de.schuetze.demo.model.Person;

/**
 * 
 * @author Torsten Jäger 2020
 *
 */
public interface PersonRepository extends JpaRepository<Person, String> {

	Person findByName(String name);

}
