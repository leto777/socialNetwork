package de.schuetze.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.schuetze.demo.model.Person;
import de.schuetze.demo.model.data.PersonRepository;

/**
 * 
 * @author Torsten Jäger 2020
 *
 */
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<Person> getAllPersons(int pageNo, int pageSize, String sortBy) {
		List<Person> personList = new ArrayList<>();
		PageRequest sortedByName = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		personRepository.findAll(sortedByName).forEach(personList::add);
		return personList;
	}

	public Person getPersonById(String id) {
		Optional<Person> person = personRepository.findById(id);
		return person.get();
	}

	public Person getPersonByName(String name) {
		Person person = personRepository.findByName(name);
		return person;
	}

	public void addPerson(String name) {
		personRepository.save(new Person(name));
	}

	public void updatePerson(String id, Person person) {
		Person personToUpdate = getPersonById(id);
		personToUpdate.setName(person.getName());
		personRepository.save(person);
	}

	public void deletePerson(String id) {
		Person personToDelete = getPersonById(id);
		personRepository.delete(personToDelete);
	}

	public void addFriend(String sourceId, String targetId) {
		updateFriendship(sourceId, targetId, Boolean.FALSE);
	}

	public void deleteFriend(String sourceId, String targetId) {
		updateFriendship(sourceId, targetId, Boolean.TRUE);
	}

	private void updateFriendship(String sourceId, String targetId, boolean isDeleteOperation) {
		Person sourcePerson = getPersonById(sourceId);
		Person targetPerson = getPersonById(targetId);

		if (sourcePerson != null && targetPerson != null) {
			if (isDeleteOperation) {
				sourcePerson.removeFriend(targetId);
				targetPerson.removeFriend(sourceId);
			} else {
				sourcePerson.addFriend(targetId);
				targetPerson.addFriend(sourceId);
			}
			personRepository.save(sourcePerson);
			personRepository.save(targetPerson);
		}
	}
}
