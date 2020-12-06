package aiss.api.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.City;
import aiss.model.repository.MapPersonRepository;
import aiss.model.repository.PersonRepository;

@Path("/city")

public class CityResource {

	public static CityResource _instance = null;
	PersonRepository repository;

	private CityResource() {
		repository = MapPersonRepository.getInstance();
	}

	public static CityResource getInstance() {
		if (_instance == null)
			_instance = new CityResource();
		return _instance;
	}

	@GET
	@Produces("application/json")
	public Collection<City> getAllCities(@QueryParam("country") String country) {
		List<City> res = new ArrayList<>();

		for (City city : repository.getAllCities())
			if (country == null || country.equals("") || city.getCountry().equals(country))
				res.add(city);

		return res;
	}

	@GET
	@Path("/{cityName}")
	@Produces("application/json")
	public City getCity(@PathParam("cityName") String cityName) {
		City city = repository.getCity(cityName);

		if (city == null)
			throw new NotFoundException("The city with code " + cityName + "was not found");

		return city;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addCity(@Context UriInfo uriInfo, City city) {

		if (city.getName() == null || "".equals(city.getName()))
			throw new BadRequestException("The name of the city must not be null");

		if (city.getCountry() == null || "".equals(city.getCountry()))
			throw new BadRequestException("The country of the city must not be null");

		repository.addCity(city);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(city.getName());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(city);

		return resp.build();
	}

	@PUT
	@Consumes("application/json")
	public Response updateCity(City city) {
		City oldPlace = repository.getCity(city.getName());

		if (oldPlace == null)
			throw new NotFoundException("The city " + city.getName() + " was not found");

		if (city.getName() != null)
			oldPlace.setName(city.getName());

		if (city.getCountry() != null)
			oldPlace.setCountry(city.getCountry());

		return Response.noContent().build();
	}

}
