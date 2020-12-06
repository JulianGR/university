package aiss.resources.tests;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.resources.FoursquareResource;

public class FoursquareTest {
	FoursquareResource fr = new FoursquareResource();

	@Test
	public void getFoursquareInfoTest() throws UnsupportedEncodingException {

		String title = "Sevilla";

		String foursquareResults = fr.getFoursquareInfo(title).getResponse().getGroups().get(0).getItems().get(19)
				.getVenue().getName();

		assertNotNull("La búsqueda no puede ser null", foursquareResults);
	}

}
