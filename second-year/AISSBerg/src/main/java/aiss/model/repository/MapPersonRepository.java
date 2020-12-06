package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Category;
import aiss.model.City;
import aiss.model.Person;

public class MapPersonRepository  implements PersonRepository{
	
	Map<String, Person> personMap;
	Map<String, City> cityMap;
	private static MapPersonRepository instance=null;
	private int index=0;			// Index to create people and places identifiers.
	
	
	public static MapPersonRepository getInstance() {
		
		if (instance==null) {
			instance = new MapPersonRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		personMap = new HashMap<String,Person>();
		cityMap = new HashMap<String,City>();
		
		// Create cities
		City sevilla = new City();
		sevilla.setName("Sevilla");
		sevilla.setCountry("ES");
		addCity(sevilla);
		
		City madrid = new City();
		madrid.setName("Madrid");
		madrid.setCountry("ES");
		addCity(madrid);
		
		
		City buenosAires = new City();
		buenosAires.setName("Buenos_Aires");
		buenosAires.setCountry("ARG");
		addCity(buenosAires);
		
		
		// Create people
		Person person1= new Person();
		person1.setName("Aaron");
		person1.setEmail("aaron43@gmail.com");
		person1.setPhone("650973887");
		addPerson(person1);
		
		Person person2 = new Person();
		person2.setName("Betty");
		person2.setEmail("bettylafea@gmail.com");
		addPerson(person2);
		
		// Add people to cities
		addPersonToCity(person1, sevilla, Category.VISITADO);
		addPersonToCity(person1, madrid, Category.ESPERANDO);
		addPersonToCity(person1, buenosAires, Category.DESEADO);
		
		addPersonToCity(person2, buenosAires,Category.VISITADO);
		addPersonToCity(person2, sevilla, Category.ESPERANDO);
	}
	
	// ------------------------------ Operaciones con person ------------------------------
	
	@Override
	public void addPerson(Person p) {
		String id = "p" + index++;	
		p.setId(id);
		personMap.put(id,p);
	}	
	
	@Override
	public void addPersonToCity(Person p, City c, Category cat) {
		Person newPerson = personMap.get(p.getId());
		newPerson.setCategory(cat);
		cityMap.get(c.getName()).addPeople(newPerson);
	}

	@Override
	public Collection<Person> getAllPerson(String cityName) {
		return cityMap.get(cityName).getPeople();
	}

	@Override
	public Collection<Person> getAllPerson() {
			return personMap.values();
	}
	//aqui esta el fallo
	
	@Override
	public Person getPersonById(String id) {
		return personMap.get(id);
	}
	
	@Override
	public void updatePerson(Person p) {
		personMap.put(p.getId(),p);
	}

	@Override
	public void deletePerson(String id) {	
		personMap.remove(id);
	}
	
	@Override
	public void deletePerson(String personId, String city) {
		Person person = personMap.get(personId);
		cityMap.get(city).deletePeople(person);
	}
	
	// ------------------------------ Operaciones con city ------------------------------

	@Override
	public void addCity(City c) {
		cityMap.put(c.getName(), c);
	}

	@Override
	public Collection<City> getAllCities() {
		return cityMap.values();
	}

	@Override
	public City getCity(String name) {
		return cityMap.get(name);
	}

	@Override
	public void updateCity(City s) {
		City city = cityMap.get(s.getName());
		city.setName(s.getName());
	}

	@Override
	public void deleteCity(String name) {
		cityMap.remove(name);
	}
	
}



