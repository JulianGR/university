package aiss.model.repository;

import java.util.Collection;

import aiss.model.Category;
import aiss.model.City;
import aiss.model.Person;

public interface PersonRepository {
		
		// Cities
		public void addCity(City c);
		public Collection<City> getAllCities();//
		public City getCity(String name);//
		public void updateCity(City s);
		public void deleteCity(String name);
				
		
		// People
		public void addPerson(Person p);
		public void addPersonToCity(Person p, City c, Category cat);
		public Collection<Person> getAllPerson();//
		public Collection<Person> getAllPerson(String cityName);
		public Person getPersonById(String personId);
		public void updatePerson(Person p);
		public void deletePerson(String personId);
		public void deletePerson(String personId, String city);
		
	}


