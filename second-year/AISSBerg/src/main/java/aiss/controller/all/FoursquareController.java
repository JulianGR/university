package aiss.controller.all;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.foursquare.FoursquareSearch;
import aiss.model.foursquare.Venue;
import aiss.model.resources.FoursquareResource;

public class FoursquareController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FoursquareController.class.getName());

	public FoursquareController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String code = request.getParameter("code");
		request.setAttribute("code", code);
		log.log(Level.INFO, "code: " + code);

		if (code == null || code.equals("")) {
			log.log(Level.SEVERE, "Fallo en code");
		}

		FoursquareResource fr = new FoursquareResource();
		String token = fr.getFoursquareToken(code).getAccessToken();
		log.log(Level.INFO, "Foursquare-Token obtenido correctamente: " + token);

		if (token == null || token.equals("")) {
			log.log(Level.SEVERE, "Fallo en token");
		}

		// lista con nombre de "aissberg" creada
		FoursquareResource frList = new FoursquareResource();
		request.setAttribute("newList", frList.createNewFoursquareList(token));

		// Cogemos todas las listas del usuario, pillamos la que tenga de nombre
		// "aissberg" (previamente creada) y le cogemos el id
		String idLista = "";
		for (int i = 0; i < fr.getFoursquareLists(token).getResponse().getLists().getGroups().get(1).getItems().size()
				- 1; i++) {
			String listName1 = fr.getFoursquareLists(token).getResponse().getLists().getGroups().get(1).getItems()
					.get(i).getName();
			if (listName1.contains("aissberg")) {
				String listId1 = fr.getFoursquareLists(token).getResponse().getLists().getGroups().get(1).getItems()
						.get(i).getId();
				idLista = listId1;
				break;
			}
		}
		if (idLista == null || idLista.equals("")) {
			log.log(Level.SEVERE, "Fallo en idLista");
		}
		request.setAttribute("idLista", idLista);

		// Aprovechamos 1 for para 2 cosas:
		// meter en una lista las venues
		// meter en una Lista los ids de las venues

		List<String> queryResultId = new ArrayList<>();

		List<Venue> venues = new ArrayList<>();

		// ================================================
		// ================================================
		// ================================================
		// ================================================

		// String city = request.getAttribute("city").toString();
		// String city = request.getParameter("city");

		// FoursquareSearch foursquareResults = fr.getFoursquareInfo(city);
		String c = SearchCityController.cityTotal;
		FoursquareSearch foursquareResults = fr.getFoursquareInfo(c);

		for (int i = 0; i < 20; i++) {

			venues.add(foursquareResults.getResponse().getGroups().get(0).getItems().get(i).getVenue());
			queryResultId.add(foursquareResults.getResponse().getGroups().get(0).getItems().get(i).getVenue().getId());
		}
		if (foursquareResults == null || venues == null || venues.isEmpty()) {
			log.log(Level.SEVERE, "Fallo en foursquareResults o venues");
		}

		request.setAttribute("venues", venues);

		request.setAttribute("queryIds", queryResultId);

		// Con la lista de ids de venues vamos haciendo llamadas para meter todas las
		// venues en la lista que creamos
		for (String venuesId : queryResultId) {
			fr.addVenueToAnyListFoursquareList(token, idLista, venuesId);
		}

		rd = request.getRequestDispatcher("foursquareSuccess.jsp");

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
