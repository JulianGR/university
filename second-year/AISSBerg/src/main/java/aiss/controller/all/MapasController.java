package aiss.controller.all;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.calendarific.CalendarificSearch;
import aiss.model.foursquare.FoursquareSearch;
import aiss.model.foursquare.Venue;
import aiss.model.resources.CalendarificResource;
import aiss.model.resources.FoursquareResource;

public class MapasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MapasController.class.getName());

	public MapasController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String lat = request.getParameter("lat");
		request.setAttribute("lat", lat);
		String lng = request.getParameter("lng");
		request.setAttribute("lng", lng);

		if (lat == null || lat.equals("") || lng == null || lng.equals("")) {
			log.log(Level.SEVERE, "Fallo en lat o lng");
		}

		String city = request.getParameter("locality");
		request.setAttribute("city", city);
		String country = request.getParameter("country");
		request.setAttribute("country", country);
		String countryShort = request.getParameter("countryShort");
		request.setAttribute("countryShort", countryShort);

		if (city == null || city.equals("") || country == null || country.equals("") || countryShort == null
				|| countryShort.equals("")) {
			log.log(Level.SEVERE, "Fallo en city, country o countryShort");
		}

		// ==============CALENDARIOS==============================
		// ==============CALENDARIOS==============================
		// ==============CALENDARIOS==============================
		// ==============CALENDARIOS==============================
		// ==============CALENDARIOS==============================

		Calendar fecha = Calendar.getInstance();
		Integer month = fecha.get(Calendar.MONTH) + 1; // Le tengo que sumar uno porque esta llamada devuelve el mes en
														// el rango [0-11]
		Integer year = fecha.get(Calendar.YEAR);

		CalendarificResource calendar = new CalendarificResource();
		CalendarificSearch calendarResult = calendar.getHolidays(countryShort, year, month);

		if (calendarResult == null) {
			log.log(Level.SEVERE, "Fallo en calendarResult");
		}

		request.setAttribute("holidays", calendarResult.getResponse().getHolidays());

		// ============== FIN DE CALENDARIOS==============================
		// ============== FIN DE CALENDARIOS==============================
		// //============== FIN DE CALENDARIOS==============================
		// //============== FIN DE CALENDARIOS==============================

		FoursquareResource fr = new FoursquareResource();
		List<Venue> venues = new ArrayList<>();
		FoursquareSearch foursquareResults = fr.getFoursquareInfo(city);

		if (foursquareResults == null) {
			log.log(Level.SEVERE, "Fallo en foursquareResults");
		}

		// Aprovechamos 1 for para 2 cosas:
		// meter en una lista las venues
		// meter en una Lista los ids de las venues
		for (int i = 0; i < 20; i++) {

			venues.add(foursquareResults.getResponse().getGroups().get(0).getItems().get(i).getVenue());

		}
		
		request.setAttribute("venues", venues);
		request.setAttribute("items", foursquareResults.getResponse().getGroups().get(0).getItems());

		rd = request.getRequestDispatcher("mapas.jsp");

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}