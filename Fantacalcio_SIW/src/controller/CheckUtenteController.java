package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.UtenteDao;
import util.Convalida;

/**
 * Servlet implementation class CheckUtenteController
 */
@WebServlet("/CheckUtenteController")
public class CheckUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckUtenteController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		String username = request.getParameter("username");

		if (new Convalida().validateParameters(username)) {

			// Utente ut = new Utente(username, null, null, null,null);
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			Utente u = utenteDao.getUtente(username);

			if (u.getUsername() == (null))
				response.getWriter().write("succes");
			else
				response.getWriter().write("error");
		} else {
			response.getWriter().write("warning");
		}

	}
}
