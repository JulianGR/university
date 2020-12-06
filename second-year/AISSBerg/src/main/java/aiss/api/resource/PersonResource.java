package aiss.api.resource;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Category;
import aiss.model.City;
import aiss.model.Person;
import aiss.model.repository.MapPersonRepository;
import aiss.model.repository.PersonRepository;

@Path("/person")

public class PersonResource {
	
	private static PersonResource _instance=null;
	PersonRepository repository;
	
	private PersonResource() {
		repository= MapPersonRepository.getInstance();

	}
	
	public static PersonResource getInstance()
	{
		if(_instance==null)
				_instance=new PersonResource();
		return _instance;
	}
	
	@GET
	@Produces("application/json")
	public Collection<Person> getAllPeople() {
		return repository.getAllPerson();
	}
	
	@GET
	@Path("/{personId}")
	@Produces("application/json")
	public Person getById(@PathParam("personId") String id) {
		Person person = repository.getPersonById(id);
		
		if (person == null) 
			throw new NotFoundException("The person with id "+ id +" was not found");	
		
		return person;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPerson(@Context UriInfo uriInfo, Person person) {
		
		if (person.getName() == null || "".equals(person.getName()))
			throw new BadRequestException("El nombre de la persona no debe ser null o vacio");
		
		if (person.getEmail() == null || "".equals(person.getEmail()))
			throw new BadRequestException("El email de la persona no debe ser null o vacio");
		
		if (person.getPhone() == null || "".equals(person.getPhone()))
			throw new BadRequestException("El telefono de la persona no debe ser null o vacio");

		repository.addPerson(person);
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(person.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(person);			
		
		return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updatePerson(Person person) {
		Person oldPerson = repository.getPersonById(person.getId());
		if (oldPerson == null) 
			throw new NotFoundException("The person with id "+ person.getId() +" was not found");			
		
		// Update name
		if (person.getName()!=null)
			oldPerson.setName(person.getName());
		
		// Update description
		if (person.getEmail()!=null)
			oldPerson.setEmail(person.getEmail());
		
		// Update phone number
		if (person.getPhone()!=null)
			oldPerson.setPhone(person.getPhone());
		
		return Response.noContent().build();
		}
	
	@DELETE
	@Path("/{personId}")
	public Response removePerson(@PathParam("personId") String id) {
		Person personToBeRemoved = repository.getPersonById(id);
		
		if (personToBeRemoved == null)
			throw new NotFoundException("The person with id " + id + " was not found");
		else
			repository.deletePerson(id);
		
		return Response.noContent().build();
		}
		
		
	@POST	
	@Path("/{personId}/{cityName}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addPerson(@Context UriInfo uriInfo,@PathParam("personId") String personId, @PathParam("cityName") String cityName, @PathParam("category") String category)	{				
			
		Person person = repository.getPersonById(personId);
		City city = repository.getCity(cityName);
		
		if (person==null)
			throw new NotFoundException("The person with id " + personId + " was not found");
			
		if (city == null)
			throw new NotFoundException("The city with name " + cityName + " was not found");
				
		repository.addPersonToCity(person, city, Category.valueOf(category));		

			// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(personId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(person);			
		return resp.build();
	}
		
		
	@DELETE
	@Path("/{personId}/{cityName}")
	public Response removePerson(@PathParam("personId") String personId, @PathParam("cityName") String cityName) {
		Person person = repository.getPersonById(personId);
		City city = repository.getCity(cityName);
		
		if (person==null)
			throw new NotFoundException("The person with id " + personId + " was not found");
			
		if (city == null)
			throw new NotFoundException("The city " + cityName + " was not found");
			
		repository.deletePerson(personId, cityName);		
			
		return Response.noContent().build();
	}

}
