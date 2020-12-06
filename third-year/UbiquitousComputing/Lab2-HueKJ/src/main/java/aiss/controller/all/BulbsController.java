package aiss.controller.all;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aiss.model.resources.HueResource;

public class BulbsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BulbsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = null;

		String bulb = request.getParameter("bulb");
		String colors = request.getParameter("color");

		HueResource hue = new HueResource();

		Boolean success = hue.changeColorHue(colors, bulb);

		System.out.println(success);

		if (success) {

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
