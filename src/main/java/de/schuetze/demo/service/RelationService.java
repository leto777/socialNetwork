package de.schuetze.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.schuetze.demo.model.Person;

/**
 * 
 * @author Torsten Jäger 2020
 *
 */
@Service
public class RelationService {

	@Autowired
	PersonService personService;

	public List<Person> getRelationship(String id) {

		List<Person> relatedPersonList = new ArrayList<Person>();

		Person person = personService.getPersonById(id);

		for (String friendId : person.getFriends()) {
			relatedPersonList.add(personService.getPersonById(friendId));
		}

		return relatedPersonList;

	}

}
