package de.schuetze.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.schuetze.demo.model.Person;
import de.schuetze.demo.service.PersonService;

/**
 * 
 * @author Torsten Jäger 2020
 *
 */
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping
	@RequestMapping("/persons")
	public List<Person> getAllPersons(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "name") String sortBy) {
		return personService.getAllPersons(pageNo, pageSize, sortBy);
	}

	@RequestMapping("/persons/{id}")
	public Person getPersonById(@PathVariable String id) {
		return personService.getPersonById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/persons/{name}")
	public void addPerson(@PathVariable String name) {
		personService.addPerson(name);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/persons/{id}")
	public void updatePerson(@RequestBody Person person, @PathVariable String id) {
		personService.updatePerson(id, person);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/persons/{id}")
	public void deletePerson(@PathVariable String id) {
		personService.deletePerson(id);
	}

}
