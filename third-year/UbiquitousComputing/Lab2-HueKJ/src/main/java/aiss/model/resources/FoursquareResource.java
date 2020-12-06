package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import aiss.model.foursquare.FoursquareSearch;

//Primero se buscan los 15 mejores sitios de una ciudad
//después con el oauth nos registramos en la cuenta de alguien
//después aparecerá un botón para añadir esos 15 mejores sitios a una lista en Foursquare
//después aparecerá un mensaje de "¡Hecho! Mira tu cuenta en Foursquare y verás tu nueva flamante lista con los 15 más interesantes sitios de esta ciudad.

public class FoursquareResource {

	private static final String CLIENT_ID = "XXXXXXXXXXX";
	private static final String CLIENT_SECRET = "XXXXXXXXXXX";
	private static final String DATE_VERSION = "20190519";

	public FoursquareSearch getFoursquareInfo(String query) throws UnsupportedEncodingException {

		ClientResource cr = null;
		FoursquareSearch fs = null;

		String title = URLEncoder.encode(query, "UTF-8");
		String uri = "https://api.foursquare.com/v2/venues/explore?near=" + title
				+ "&section=topPicks&limit=7&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v="
				+ DATE_VERSION;
		try {
			cr = new ClientResource(uri);
			fs = cr.get(FoursquareSearch.class);
		} catch (ResourceException re) {
			System.err.println("Error al obtener la información. Código de error: " + cr.getResponse().getStatus());
		}

		return fs;
	}

}
