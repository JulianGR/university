package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.wikiSearch.WikiPages;
import aiss.model.wikiSummary.Summary;

public class WikiResource {

	private static final Logger log = Logger.getLogger(WikiResource.class.getName());

	public Summary getSummary(String page) throws UnsupportedEncodingException {

		String namePage = getPages(page).getQuery().getSearch().get(0).getTitle().replaceAll(" ", "_");
		String uri = "https://es.wikipedia.org/api/rest_v1/page/summary/" + namePage;

		log.log(Level.FINE, "WIKIPEDIA SUMMARY URI:" + uri);
		ClientResource cr = new ClientResource(uri);
		Summary sum = cr.get(Summary.class);
		return sum;
	}

	public WikiPages getPages(String name) throws UnsupportedEncodingException {

		String namePage = URLEncoder.encode(name, "UTF-8");

		String uri = "https://es.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + namePage
				+ "&utf8=&format=json";

		log.log(Level.FINE, "WIKIPEDIA PAGES URI:" + uri);
		ClientResource cr = new ClientResource(uri);
		WikiPages wp = cr.get(WikiPages.class);
		return wp;
	}

}
