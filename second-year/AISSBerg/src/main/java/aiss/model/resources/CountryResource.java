package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.countries.CountrySearch;

public class CountryResource {

	private static final Logger log = Logger.getLogger(CountryResource.class.getName());

	public CountrySearch getCountry(String query) throws UnsupportedEncodingException {

		String country = URLEncoder.encode(query, "UTF-8").trim();
		String url = "https://restcountries.eu/rest/v2/alpha/" + country;
		log.log(Level.INFO, "getCountry URI: " + url);
		ClientResource cr = new ClientResource(url);
		CountrySearch countrySearch = cr.get(CountrySearch.class);
		return countrySearch;
	}

}
