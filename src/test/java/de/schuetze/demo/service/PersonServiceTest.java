package de.schuetze.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import de.schuetze.demo.model.Person;
import de.schuetze.demo.model.data.PersonRepository;

/**
 * 
 * @author Torsten Jäger 2020
 *
 */
@RunWith(SpringRunner.class)
public class PersonServiceTest {

	private Optional<Person> person;

	@org.springframework.boot.test.context.TestConfiguration
	static class TestConfiguration {
		@Bean
		public PersonService getService() {
			return new PersonService();
		}
	}

	@Autowired
	private PersonService personService;

	@MockBean
	private PersonRepository personRepository;

	@Before
	public void setUp() throws Exception {
		person = Optional.of(new Person("Felix"));
		Mockito.when(personRepository.findById(person.get().getId())).thenReturn(person);
	}

	@Test
	public void test() {
		Person personFound = personService.getPersonById(person.get().getId());
		assertThat(personFound != null);
	}

}
