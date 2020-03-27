package de.schuetze.demo.model;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author Torsten Jäger 2020
 *
 */
@Entity
public class Person {

	@Id
	private String id;
	private String name;

	// @JsonIgnore
	private String friends = "";

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
	}

	public Person(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addFriend(String id) {
		friends += id + ",";
	}

	public List<String> getFriends() {
		return Arrays.asList(friends.split(","));
	}

	public void removeFriend(String id) {
		friends = friends.replace(id + ",", "");
	}
}
