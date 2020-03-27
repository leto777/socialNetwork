package de.schuetze.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.schuetze.demo.model.Person;
import de.schuetze.demo.service.PersonService;

/**
 * 
 * @author Torsten Jäger 2020
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PersonService personService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {

		List<Person> allPersonList = new ArrayList<Person>();
		allPersonList.add(new Person("Felix"));

		when(personService.getAllPersons(0, 3, "name")).thenReturn(allPersonList);

		mvc.perform(get("/persons?pageNo=0&pageSize=3&sortBy=name")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$[0].name", is(allPersonList.get(0).getName())));

	}

}
