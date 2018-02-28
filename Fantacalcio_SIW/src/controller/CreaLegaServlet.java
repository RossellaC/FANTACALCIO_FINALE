package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lega;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.LegaDao;

/**
 * Servlet implementation class CreaLegaServlet
 */
@WebServlet("/creaLega")
public class CreaLegaServlet extends HttpServlet {

	private static final long serialVersionUID = -3153463133959075814L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public CreaLegaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pagine/creaLega.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Inserimento di una lega da parte di un utente.
		String nomeLega = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		Float budget = Float.parseFloat(request.getParameter("budget"));
		LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
		Utente utente = (Utente) request.getSession().getAttribute("utente");  
		System.out.println("sono nella servlet crea lega" + utente.getId());
		if(nomeLega != null && !nomeLega.equals("") && descrizione != null && !descrizione.equals("") 
				&& utente != null) {
			Lega l = new Lega(0L, budget, nomeLega, descrizione, utente.getId());
			legaDao.save(l);
			request.getRequestDispatcher("/WEB-INF/pagine/successoOperazione.jsp").forward(request, response);
		} else {
			response.sendRedirect("ErrorePag.jsp");
		}
	}

}
