package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.fs.fsSearch;

//Primero se buscan los 15 mejores sitios de una ciudad
//después con el oauth nos registramos en la cuenta de alguien
//después aparecerá un botón para añadir esos 15 mejores sitios a una lista en Foursquare
//después aparecerá un mensaje de "¡Hecho! Mira tu cuenta en Foursquare y verás tu nueva flamante lista con los 15 más interesantes sitios de esta ciudad.

public class fsResource {

	private static final String CLIENT_ID = "XXXXXXXXXXX";
	private static final String CLIENT_SECRET = "XXXXXXXXXXX";
	private static final String DATE_VERSION = "20190519";

	public fsSearch getVenueInfo(String query) throws UnsupportedEncodingException {

		ClientResource cr = null;
		fsSearch fs = null;

		String title = URLEncoder.encode(query, "UTF-8");
		String uri = "https://api.foursquare.com/v2/venues/" + title + "?client_id=" + CLIENT_ID + "&client_secret="
				+ CLIENT_SECRET + "&v=" + DATE_VERSION;

		try {
			cr = new ClientResource(uri);
			fs = cr.get(fsSearch.class);
		} catch (ResourceException re) {
			System.err.println("Error al obtener la información. Código de error: " + cr.getResponse().getStatus());
		}

		return fs;
	}

}
