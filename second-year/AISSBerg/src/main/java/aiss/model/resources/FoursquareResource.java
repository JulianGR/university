package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.foursquare.FoursquareSearch;
import aiss.model.foursquare.FoursquareToken;
import aiss.model.foursquare.list.FoursquareList;

//Primero se buscan los 15 mejores sitios de una ciudad
//después con el oauth nos registramos en la cuenta de alguien
//después aparecerá un botón para añadir esos 15 mejores sitios a una lista en Foursquare
//después aparecerá un mensaje de "¡Hecho! Mira tu cuenta en Foursquare y verás tu nueva flamante lista con los 15 más interesantes sitios de esta ciudad.

public class FoursquareResource {

	private static final String CLIENT_ID = "XXXXXXXXXXX";
	private static final String CLIENT_SECRET = "XXXXXXXXXXX";
	private static final String CALLBACK_URI = "http://aissberg-238915.appspot.com/FoursquareController";
	private static final String DATE_VERSION = "20190519";
	private static final Logger log = Logger.getLogger(FoursquareResource.class.getName());

	public FoursquareSearch getFoursquareInfo(String query) throws UnsupportedEncodingException {

		ClientResource cr = null;
		FoursquareSearch fs = null;

		String title = URLEncoder.encode(query, "UTF-8");
		String uri = "https://api.foursquare.com/v2/venues/explore?near=" + title
				+ "&section=topPicks&limit=20&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v="
				+ DATE_VERSION;
		log.log(Level.INFO, "getFoursquareInfo URI: " + uri);

		try {
			cr = new ClientResource(uri);
			fs = cr.get(FoursquareSearch.class);
		} catch (ResourceException re) {
			System.err.println("Error al obtener la información. Código de error: " + cr.getResponse().getStatus());
		}

		log.log(Level.FINE, "getFoursquareInfo(" + title + ") completado con éxito");
		return fs;
	}

	public FoursquareToken getFoursquareToken(String code) throws UnsupportedEncodingException {

		ClientResource cr = null;
		FoursquareToken ft = null;

		String uri = "https://foursquare.com/oauth2/access_token?client_id=" + CLIENT_ID + "&client_secret="
				+ CLIENT_SECRET + "&grant_type=authorization_code&redirect_uri=" + CALLBACK_URI + "&code=" + code;
		log.log(Level.INFO, "FoursquareToken URI: " + uri);

		try {
			cr = new ClientResource(uri);
			ft = cr.get(FoursquareToken.class);
		} catch (ResourceException re) {
			System.err.println("Error al obtener el token, código de error: " + cr.getResponse().getStatus());
		}

		log.log(Level.FINE, "getFoursquareToken(" + code + ") completado con éxito");
		return ft;
	}

	public FoursquareList getFoursquareLists(String token) throws UnsupportedEncodingException {

		ClientResource cr = null;
		FoursquareList fl = null;

		String uri = "https://api.foursquare.com/v2/users/self/lists?oauth_token=" + token + "&v=" + DATE_VERSION;
		log.log(Level.INFO, "FoursquareList URI: " + uri);

		try {
			cr = new ClientResource(uri);
			fl = cr.get(FoursquareList.class);
		} catch (ResourceException re) {
			System.err.println("Error al obtener la lista, código de error: " + cr.getResponse().getStatus());
		}

		log.log(Level.FINE, "getFoursquareToken(" + token + ") completado con éxito");
		return fl;

	}

	public Boolean createNewFoursquareList(String token) throws UnsupportedEncodingException {

		ClientResource cr = null;
		boolean success = true;

		String uri = "https://api.foursquare.com/v2/lists/add?oauth_token=" + token + "&name=aissberg&v="
				+ DATE_VERSION;
		log.log(Level.INFO, "addFoursquareList URI: " + uri);

		try {
			cr = new ClientResource(uri);
			cr.post(" ");
		} catch (ResourceException re) {
			System.err.println("Error al crear nueva lista, código de error: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}

		log.log(Level.FINE, "addFoursquareList completado con éxito");
		return success;
	}

	public Boolean createNewFoursquareListTest(String token) throws UnsupportedEncodingException {

		ClientResource cr = null;
		boolean success = true;

		String uri = "https://api.foursquare.com/v2/lists/add?oauth_token=" + token + "&name=aissbergTEST&v="
				+ DATE_VERSION;
		log.log(Level.INFO, "addFoursquareList URI: " + uri);

		try {
			cr = new ClientResource(uri);
			cr.post(" ");
		} catch (ResourceException re) {
			System.err.println("Error al crear nueva lista, código de error: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}

		log.log(Level.FINE, "addFoursquareList completado con éxito");
		return success;
	}

	public Boolean addVenueToAnyListFoursquareList(String token, String listId, String venueId)
			throws UnsupportedEncodingException {

		ClientResource cr = null;
		boolean success = true;

		String uri = "https://api.foursquare.com/v2/lists/" + listId + "/additem?venueId=" + venueId + "&v="
				+ DATE_VERSION + "&oauth_token=" + token + "#_=_";
		log.log(Level.INFO, "addVenueToAnyListFoursquareList:" + uri);

		try {
			cr = new ClientResource(uri);
			cr.post(" ");
		} catch (ResourceException re) {
			System.err.println("Error al añadir el lugar a la lista de favoritos, código de error: "
					+ cr.getResponse().getStatus());
			success = false;
			throw re;
		}

		log.log(Level.FINE, "addVenueToAnyListFoursquareList(" + venueId + ") de la lista con ID: " + listId
				+ " completado con éxito");
		return success;
	}

}
