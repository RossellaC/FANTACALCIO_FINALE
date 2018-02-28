package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.InvitoLega;
import model.Lega;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.InvitoDao;
import persistence.dao.LegaDao;

/**
 * Servlet implementation class PannelloUtenteServlet
 */
@WebServlet("/pannelloUtente")
public class PannelloUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PannelloUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente != null) {
			LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
			InvitoDao invitiDao = DatabaseManager.getInstance().getDaoFactory().getInvitoDao();
			List<Lega> leghe = legaDao.legheUtente(utente.getId());
			List<InvitoLega> inviti = invitiDao.getInvitiRicevutiUtente(utente.getId());
			List<InvitoLega> invitiSquadre = invitiDao.invitiSenzaSquadra(utente.getId());
			request.setAttribute("leghe", leghe);
			request.setAttribute("inviti", inviti);
			request.setAttribute("invitiSquadre", invitiSquadre);
			request.getRequestDispatcher("/WEB-INF/pagine/utentePanel2.jsp").forward(request, response);
		} else {
			response.sendRedirect("ErrorePag.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
