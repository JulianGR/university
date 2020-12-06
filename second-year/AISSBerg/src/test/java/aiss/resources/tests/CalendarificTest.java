package aiss.resources.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import aiss.model.calendarific.CalendarificSearch;
import aiss.model.resources.CalendarificResource;

public class CalendarificTest {

	CalendarificResource calendarificResource = new CalendarificResource();

	@Test
	public void testGetHolidays() throws IOException {
		String query = "ES";

		System.out.println("Test Calendarific: Probando búsqueda de " + query);
		CalendarificSearch res = calendarificResource.getHolidays(query, 2018, 11);
		CalendarificSearch res2 = calendarificResource.getHolidays(query, 2018, 12);
		assertTrue("Las fiestas no pueden ser null", (res != null) || (res2 != null));

	}

}
