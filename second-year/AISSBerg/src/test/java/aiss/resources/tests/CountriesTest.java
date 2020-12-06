package aiss.resources.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import aiss.model.countries.CountrySearch;
import aiss.model.resources.CountryResource;

public class CountriesTest {

	CountryResource countryResource = new CountryResource();

	@Test
	public void testGetCountry() throws IOException {
		String query = "ES";
		System.out.println("Test Country: Probando búsqueda de país de " + query);
		CountrySearch res = countryResource.getCountry(query);
		String condition = countryResource.getCountry(query).getAlpha2Code();
		assertNotNull("El país no puede ser null", res);
		assertEquals(query, condition);
	}

}
