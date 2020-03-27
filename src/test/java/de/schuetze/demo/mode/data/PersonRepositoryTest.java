package de.schuetze.demo.mode.data;

import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import de.schuetze.demo.model.Person;
import de.schuetze.demo.model.data.PersonRepository;

/**
 * 
 * @author Torsten Jaeger 2020
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PersonRepository personRepository;

	@Test
	void findPersonById() {

		Person person = new Person("Felix");
		String id = person.getId();

		entityManager.persist(person);
		entityManager.flush();

		Optional<Person> personFound = personRepository.findById(id);

		assertFalse(personFound.isEmpty());

	}

}
