package aiss.resources.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import aiss.model.resources.WikiResource;

public class WikipediaTest {

	WikiResource wikiResource = new WikiResource();

	@Test
	public void testGetSummary() throws IOException {
		String query = "Lisboa";
		System.out.println("Test Wikipedia: Probando búsqueda de " + query);
		String res = wikiResource.getSummary(query).getExtract();
		assertNotNull("El país no puede ser null", res);
		assertTrue(res.contains(query));
	}

}
