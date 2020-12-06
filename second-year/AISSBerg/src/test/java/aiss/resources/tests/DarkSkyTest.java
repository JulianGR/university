package aiss.resources.tests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import aiss.model.resources.DarkSkyResource;

public class DarkSkyTest {

	DarkSkyResource darkSkyResource = new DarkSkyResource();

	@Test
	public void testGetWeather() throws IOException {
		String lat = "37.8267";
		String lng = "-122.4233";
		System.out.println("Test DarkSky: Probando búsqueda con " + lat + lng);
		String res = darkSkyResource.getWeather(lat, lng).getCurrently().getSummary();
		assertNotNull("El summary no puede ser null", res);
	}

}
