package aiss.controller.all;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aiss.model.foursquare.FoursquareSearch;
import aiss.model.fs.fsSearch;
import aiss.model.resources.FoursquareResource;
import aiss.model.resources.fsResource;

public class MapasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MapasController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String city = request.getParameter("locality");
		request.setAttribute("city", city);
		String country = request.getParameter("country");
		request.setAttribute("country", country);
		String countryShort = request.getParameter("countryShort");
		request.setAttribute("countryShort", countryShort);

		FoursquareResource fr = new FoursquareResource();

		List<String> venues = new ArrayList<>();
		FoursquareSearch foursquareResults = fr.getFoursquareInfo(city);

		for (int i = 0; i < 6; i++) {

			venues.add(foursquareResults.getResponse().getGroups().get(0).getItems().get(i).getVenue().getId());

		}

		request.setAttribute("items", foursquareResults.getResponse().getGroups().get(0).getItems());

		fsResource fs = new fsResource();
		List<aiss.model.fs.Venue> venuesDetail = new ArrayList<>();

		for (String s : venues) {

			fsSearch fsResults = fs.getVenueInfo(s);

			venuesDetail.add(fsResults.getResponse().getVenue());

		}

		request.setAttribute("venuesDetail", venuesDetail);

		rd = request.getRequestDispatcher("/afterSearch.jsp");

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}