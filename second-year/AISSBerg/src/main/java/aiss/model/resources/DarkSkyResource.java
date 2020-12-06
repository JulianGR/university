package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.resource.ClientResource;

import aiss.model.darksky.DarkSky;

public class DarkSkyResource {

	private static final String DARKSKY_API_KEY = "XXXXXXXXXXX";
	private static final Logger log = Logger.getLogger(DarkSkyResource.class.getName());

	public DarkSky getWeather(String lat, String lon) throws UnsupportedEncodingException {

		String url = "https://api.darksky.net/forecast/" + DARKSKY_API_KEY + "/" + lat + "," + lon
				+ "?lang=es&exclude=minutely,hourly,alerts,flags&units=si";
		log.log(Level.INFO, "getWeather URI: " + url);
		ClientResource cr = new ClientResource(url);
		DarkSky weatherSearch = cr.get(DarkSky.class);
		return weatherSearch;

	}
}
