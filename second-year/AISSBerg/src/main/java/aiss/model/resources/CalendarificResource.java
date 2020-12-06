package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.calendarific.CalendarificSearch;

public class CalendarificResource {

	private static final String CALENDARIFIC_API_KEY = "XXXXXXXXXXX";
	private static final Logger log = Logger.getLogger(CalendarificResource.class.getName());

	public CalendarificSearch getHolidays(String country, Integer year, Integer month)
			throws UnsupportedEncodingException {


		String url = "https://calendarific.com/api/v2/holidays?api_key=" + CALENDARIFIC_API_KEY + "&country=" + country
				+ "&year=" + year + "&month=" + month + "&type=national";
		log.log(Level.INFO, "getHolidays URI: " + url);

		ClientResource cr = new ClientResource(url);
		CalendarificSearch holidaysSearch = cr.get(CalendarificSearch.class);
		return holidaysSearch;
	}

}
