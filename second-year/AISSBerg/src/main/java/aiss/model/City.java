package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class City {
	
	private String name;
	private String country;
	private List<Person> people;
	
	public City() {
	}

	public City(String name, String country, List<Person> p) {
		this.name = name;
		this.people = p;
		this.country = country;
	}
	
	public City(String name, String country) {
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public List<Person> getPeople() {
		return people;
	}
	
	public Person getPeople(String id) {
		if (people == null)
			return null;
		
		Person person = null;
		for(Person p: people)
			if (p.getId().equals(id)) {
				person = p;
				break;
			}
		
		return person;
	}

	public void addPeople(Person p) {
		if (people == null)
			people = new ArrayList<Person>();
		people.add(p);
	}
	
	public void deletePeople(Person p) {
		people.remove(p);
	}
	
	public void deletePeople(String id) {
		Person p = getPeople(id);
		if (p!=null)
			people.remove(p);
	}
	
}
