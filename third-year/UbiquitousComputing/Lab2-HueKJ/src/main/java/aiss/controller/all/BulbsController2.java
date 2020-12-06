package aiss.controller.all;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aiss.model.resources.HueResource;

public class BulbsController2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BulbsController2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = null;

		String colorIsOpen = request.getParameter("colorIsOpen");
		if (colorIsOpen.contentEquals("true")) {
			colorIsOpen = "green";
		} else {
			colorIsOpen = "red";
		}

		String colorRating = request.getParameter("colorRating");
		Double rating = new Double(colorRating);

		if (0 < rating && rating <= 5) {
			colorRating = "blue";
		} else if (5 < rating && rating <= 7) {
			colorRating = "purple";
		} else if (7 < rating && rating <= 9) {
			colorRating = "orange";
		} else {
			colorRating = "yellow";
		}

		HueResource hue = new HueResource();

		Boolean success = hue.changeColorHue(colorRating, "bulb1");
		Boolean success2 = hue.changeColorHue(colorIsOpen, "bulb2");

		if (success && success2) {

			rd = request.getRequestDispatcher("/index.jsp");
		} else {

			rd = request.getRequestDispatcher("/error.jsp");
		}

		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
