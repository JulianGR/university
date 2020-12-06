package aiss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Index.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
		out.println("<meta name=\"description\" content=\"Mashup project por AISS\">");
		out.println("<meta name=\"author\" content=\"AISSBerg\">");
		out.println(
				"<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
		out.println("<title>AISSBerg</title>");

		System.out.println("<script type=\"text/javascript\">");
		System.out.println("function stopRKey(evt) {");
		System.out.println("var evt = (evt) ? evt : ((event) ? event : null);");
		System.out.println("var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);");
		System.out.println("if ((evt.keyCode == 13) && (node.type==\"text\"))  {return false;}");
		System.out.println("}");
		System.out.println("document.onkeypress = stopRKey;");
		System.out.println("</script>");
		out.println("</head>");

		out.println("<body id=\"bodyDelInicio\">");
		out.println(
				"<img class=\"fotoPrincipal\" src=\"./images/IconoPNG.png\" height=\"10%\"width=\"10%\" alt=\"AISSBerg\">");
		out.println("<h1 class=\"titulo\">AISSBerg</h1>");
		out.println("<div id='searchDiv'>");
		out.println("<form class='barraBusqueda' action='/SearchCityController' method='post'>");
		out.println(
				"<input id=\"searchMapInput\" name=\"searchQuery\" class=\"mapControls\" type=\"text\" onkeypress=\"return event.keyCode!=13\" placeholder=\"Introduce una localización\" required>");
		out.println("<input id=\"city\" name=\"locality\" type=\"hidden\">");
		out.println("<input id=\"country\" name=\"country\" type=\"hidden\">");
		out.println("<input id=\"countryShort\" name=\"countryShort\" type=\"hidden\">");
		out.println(
				"<input type=\"submit\" class=\"btn btn-warning\" name=\"searchBtn\" title=\"search\" value=\"Buscar\">");
		out.println("</form>");
		out.println("</div>");
		out.println("<script>");
		out.println("var autocomplete;");
		out.println("function initMap() {");
		out.println("var input = document.getElementById('searchMapInput');");
		out.println("var options = {types: ['(regions)'], };");
		out.println("autocomplete = new google.maps.places.Autocomplete(input, options);");
		out.println("autocomplete.setFields(['address_component']);");
		out.println("autocomplete.addListener('place_changed', fillInAddress);");
		out.println("}");
		out.println("function fillInAddress() {");
		out.println("var place = autocomplete.getPlace();");
		out.println("var town = extractFromAdress(place.address_components, \"locality\");");
		out.println("var country = extractFromAdress(place.address_components, \"country\");");
		out.println("var countryShort = extractFromAdress2(place.address_components, \"country\");");
		out.println("document.getElementById('city').value = town;");
		out.println("document.getElementById('country').value = country;");
		out.println("document.getElementById('countryShort').value = countryShort;");
		out.println("}");
		out.println("function extractFromAdress(components, type) {");
		out.println("for (var i = 0; i < components.length; i++)");
		out.println("for (var j = 0; j < components[i].types.length; j++)");
		out.println("if (components[i].types[j] == type) return components[i].long_name;");
		out.println("return \"\";}");

		out.println("function extractFromAdress2(components, type) {");
		out.println("for (var i = 0; i < components.length; i++)");
		out.println("for (var j = 0; j < components[i].types.length; j++)");
		out.println("if (components[i].types[j] == type) return components[i].short_name;");
		out.println("return \"\";}");
		out.println("</script>");
		out.println("<script");
		out.println(
				"src=\"https://maps.googleapis.com/maps/api/js?key=xxx&libraries=places&callback=initMap\"");
		out.println("async defer></script>");
		out.println("<footer>");
		out.println("<div class=\"footer-content\">");
		out.println(
				"Proyecto para la asignatura de AISS. Hecho con ❤️  desde Sevilla, por el grupo de desarrollo AISSBerg. <a");
		out.println("href=\"https://aissberg-238915.appspot.com/docs/index.html\">Nuestra API</a>");
		out.println("</div>");
		out.println("</footer>");

		out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\n"
				+ "		integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\n"
				+ "		crossorigin=\"anonymous\"></script>");

		out.println(
				"<script\n" + "		src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"\n"
						+ "		integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\"\n"
						+ "		crossorigin=\"anonymous\"></script>");

		out.println(
				"<script\n" + "		src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"\n"
						+ "		integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\"\n"
						+ "		crossorigin=\"anonymous\"></script>");

		out.println("<video id='video' autoplay='autoplay' muted loop>");

		out.println("<source src=\"./videos/bgVideo.mp4\" /></source>");
		out.println("Tu navegador no soporta la etiqueta HTML \"video\". Te sugerimos que actualices tu navegador.");
		out.println("</video>");
		out.println("</body>");
		out.println("</html>");
		log.log(Level.FINE, "Index procesado con éxito");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
