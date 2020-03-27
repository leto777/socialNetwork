package de.schuetze.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.schuetze.demo.model.Person;
import de.schuetze.demo.service.PersonService;
import de.schuetze.demo.service.RelationService;

/**
 * 
 * @author Torsten Jäger 2020
 *
 */
@RestController
public class RelationController {

	@Autowired
	RelationService relationService;
	
	@Autowired
	PersonService personService;
	
	@RequestMapping("/relations/{id}")
	public List<Person> getRelationsFromPersonById(@PathVariable String id) {
		return relationService.getRelationship(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/relations/{sourceId}/{targetId}")
	public void updateRelationship(@PathVariable String sourceId, @PathVariable String targetId) {
		personService.addFriend(sourceId, targetId);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/relations/{sourceId}/{targetId}")
	public void deleteRelationship(@PathVariable String sourceId, @PathVariable String targetId) {
		personService.deleteFriend(sourceId, targetId);
	}
	
}
