package aiss.controller.all;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.countries.CountrySearch;
import aiss.model.darksky.DarkSky;

import aiss.model.resources.CountryResource;
import aiss.model.resources.DarkSkyResource;

import aiss.model.resources.WikiResource;

import aiss.model.wikiSummary.Summary;

public class SearchCityController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SearchCityController.class.getName());

	public SearchCityController() {
		super();
	}

	public static String cityTotal;

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String city = request.getParameter("locality");
		cityTotal = city;
		String country = request.getParameter("country");
		String countryShort = request.getParameter("countryShort");

		if (city == null || city.equals("") || country == null || country.equals("") || countryShort == null
				|| countryShort.equals("")) {
			log.log(Level.SEVERE, "Fallo en city, country o countryShort");
		}

		WikiResource wikipedia = new WikiResource();
		Summary wikipediaResult = wikipedia.getSummary(city);

		if (wikipediaResult == null) {
			log.log(Level.SEVERE, "Fallo en wikipediaResult");
		}

		log.log(Level.INFO, "Buscando tiempo de " + city);
		DarkSkyResource darkSky = new DarkSkyResource();
		DarkSky darkSkyResult = darkSky.getWeather(wikipediaResult.getCoordinates().getLat().toString(),
				wikipediaResult.getCoordinates().getLon().toString());

		if (darkSkyResult == null) {
			log.log(Level.SEVERE, "Fallo en darkSkyResult");
		}

		CountryResource countryR = new CountryResource();
		CountrySearch countryResult = countryR.getCountry(countryShort);

		if (countryResult == null) {
			log.log(Level.SEVERE, "Fallo en countryResult");
		}

//		 Comprobamos los resultados y en caso no obtenerlos se redirigirá a la página
//		 de error

		if (wikipediaResult != null) {
			request.setAttribute("wikiResults", wikipediaResult);
			request.setAttribute("coordenadas", wikipediaResult.getCoordinates());
			request.setAttribute("country", country);
			request.setAttribute("countryShort", countryShort);
			request.setAttribute("pais", countryResult);
			request.setAttribute("tiempo", darkSkyResult);

			log.log(Level.FINE, "Búsqueda procesada correctamente.");
			rd = request.getRequestDispatcher("afterSearch.jsp");
		} else {
			log.log(Level.WARNING, "Error al realizar la búsqueda");
			rd = request.getRequestDispatcher("/error.jsp");
		}

		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
